package com.arsh.lastfmclient.data.api

import com.arsh.lastfmclient.BuildConfig
import com.arsh.lastfmclient.data.model.Topalbums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FMClientService {

    @GET("2.0/")
    suspend fun getTopAlbums(
        @Query("method")
        method:String = BuildConfig.method,
        @Query("artist")
        Artist:String = BuildConfig.artist,
        @Query("api_key")
        apiKey:String = BuildConfig.API_KEY
    ): Response<Topalbums>
}