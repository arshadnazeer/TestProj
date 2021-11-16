package com.arsh.lastfmclient.data.repository.search.datasource

import com.arsh.lastfmclient.data.model.album.Albums
import com.arsh.lastfmclient.data.model.album.Artist
import com.arsh.lastfmclient.data.model.search.SearchResult
import retrofit2.Response

interface SearchRemoteDataSource {
    suspend fun getArtists(artist : String) : Response<SearchResult>
}