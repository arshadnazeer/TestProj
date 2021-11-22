package com.arsh.lastfmclient.data.repository.album.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.album.Albums
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
import retrofit2.Response

/**
 * Class implementing the [AlbumRemoteDataSource] interface
 */
class AlbumRemoteDataSourceImpl(
    private val fmClientService: FMClientService
) : AlbumRemoteDataSource {
    override suspend fun getAlbums(artistName: String): Response<Albums> =
        fmClientService.getTopAlbums(artist = artistName)
}