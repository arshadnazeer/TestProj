package com.arsh.lastfmclient.data.repository.album.datasource

import com.arsh.lastfmclient.data.model.album.Albums
import retrofit2.Response

interface AlbumRemoteDataSource {
    suspend fun getAlbums(artistName : String) : Response<Albums>
}