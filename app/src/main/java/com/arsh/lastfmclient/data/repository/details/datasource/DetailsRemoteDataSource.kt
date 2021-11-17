package com.arsh.lastfmclient.data.repository.details.datasource

import com.arsh.lastfmclient.data.model.details.Details
import com.arsh.lastfmclient.data.model.search.SearchResult
import retrofit2.Response

interface DetailsRemoteDataSource {
    suspend fun getAlbumDetails(album : String, artist : String) : Response<Details>
}