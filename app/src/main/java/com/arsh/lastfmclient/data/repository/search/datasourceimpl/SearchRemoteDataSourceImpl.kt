package com.arsh.lastfmclient.data.repository.search.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.album.Albums
import com.arsh.lastfmclient.data.model.album.Artist
import com.arsh.lastfmclient.data.model.search.Artistmatches
import com.arsh.lastfmclient.data.model.search.SearchResult
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import retrofit2.Response

class SearchRemoteDataSourceImpl(
    private val fmClientService: FMClientService,
    private val method: String,
    private val apikey: String,
    private val format: String,
) : SearchRemoteDataSource {
    override suspend fun getArtists(artist: String):
            Response<SearchResult> = fmClientService.getArtists(method, artist, apikey, format)
}