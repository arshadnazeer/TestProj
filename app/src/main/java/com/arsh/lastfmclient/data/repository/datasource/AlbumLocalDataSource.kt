package com.arsh.lastfmclient.data.repository.datasource

import com.arsh.lastfmclient.data.model.Album

interface AlbumLocalDataSource {
    suspend fun getAlbumsFromDB():List<Album>
    suspend fun saveAlbumsToDB(album: List<Album>)
    suspend fun clearAll()
}