package com.arsh.lastfmclient.data.api

import com.arsh.lastfmclient.BuildConfig
import com.arsh.lastfmclient.data.model.album.Albums
import com.arsh.lastfmclient.data.model.details.Details
import com.arsh.lastfmclient.data.model.search.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service Interface with URL Endpoints Helps Get Data From the LastFMClient API
 */
interface FMClientService {

    @GET("2.0/")
    suspend fun getTopAlbums(
        @Query("method")
        method: String = BuildConfig.method_topalbums,
        @Query(value = "artist")
        artist: String,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("format")
        format: String = BuildConfig.format,

        ): Response<Albums>

    @GET("2.0/")
    suspend fun getArtists(
        @Query("method")
        method: String = BuildConfig.method_search,
        @Query("artist")
        artist: String,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("format")
        format: String = BuildConfig.format,

        ): Response<SearchResult>


    @GET("2.0/")
    suspend fun getDetails(
        @Query("method")
        method: String = BuildConfig.method_topalbums_getinfo,
        @Query("artist")
        Artist: String,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("album")
        album: String,
        @Query("format")
        format: String = BuildConfig.format,

        ): Response<Details>
}