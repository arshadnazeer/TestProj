package com.arsh.lastfmclient.data.repository.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.Topalbums
import com.arsh.lastfmclient.data.repository.datasource.AlbumRemoteDataSource
import retrofit2.Response

class AlbumRemoteDataSourceImpl(
    private val fmClientService: FMClientService,
    private val method: String,
    private val artist: String,
    private val apikey: String
    ) : AlbumRemoteDataSource {
    override suspend fun getAlbums(): Response<Topalbums> =  fmClientService.getTopAlbums(method,artist,apikey)
}