package com.arsh.lastfmclient.data.repository.album.datasourceimpl

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.model.album.Albums
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
import retrofit2.Response

class AlbumRemoteDataSourceImpl(
    private val fmClientService: FMClientService,
    private val method: String,
    private val artist: String,
    private val apikey: String,
    private val format: String,
    ) : AlbumRemoteDataSource {
    override suspend fun getAlbums(): Response<Albums> =  fmClientService.getTopAlbums(method,artist,apikey,format)
}