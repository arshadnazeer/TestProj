package com.arsh.lastfmclient.data.repository.details.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.details.Details
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsRemoteDataSource
import retrofit2.Response

/**
 * Class implementing the [DetailsRemoteDataSource] interface
 */
class DetailsRemoteDataSourceImpl(
    private val fmClientService: FMClientService
) : DetailsRemoteDataSource {
    override suspend fun getAlbumDetails(album: String, artist: String): Response<Details> =
        fmClientService.getDetails(album = album, Artist = artist)
}