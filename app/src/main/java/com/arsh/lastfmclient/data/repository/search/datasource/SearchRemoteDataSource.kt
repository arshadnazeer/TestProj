package com.arsh.lastfmclient.data.repository.search.datasource

import com.arsh.lastfmclient.data.model.search.SearchResult
import retrofit2.Response

/**
 * Interface to get the albums from the API
 */
interface SearchRemoteDataSource {
    suspend fun getArtists(artist: String): Response<SearchResult>
}