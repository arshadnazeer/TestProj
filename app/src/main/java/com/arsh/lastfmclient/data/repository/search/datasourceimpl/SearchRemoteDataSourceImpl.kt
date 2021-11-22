package com.arsh.lastfmclient.data.repository.search.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.search.SearchResult
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsRemoteDataSource
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import retrofit2.Response

/**
 * Class implementing the [SearchRemoteDataSource] interface
 */
class SearchRemoteDataSourceImpl(
    private val fmClientService: FMClientService
) : SearchRemoteDataSource {
    override suspend fun getArtists(artist: String):
            Response<SearchResult> = fmClientService.getArtists(artist = artist)
}