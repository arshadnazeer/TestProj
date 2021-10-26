package com.arsh.lastfmclient.data.repository.datasource

import com.arsh.lastfmclient.data.model.Topalbums
import retrofit2.Response

interface AlbumRemoteDataSource {
    suspend fun getAlbums() : Response<Topalbums>
}