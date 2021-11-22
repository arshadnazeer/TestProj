package com.arsh.lastfmclient.data.repository.details.datasource

import com.arsh.lastfmclient.data.model.details.Details
import retrofit2.Response

/**
 * Interface to get the albums from the API
 */
interface DetailsRemoteDataSource {
    suspend fun getAlbumDetails(album: String, artist: String): Response<Details>
}