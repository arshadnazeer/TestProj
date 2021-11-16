package com.arsh.lastfmclient.data.repository.album.datasource

import com.arsh.lastfmclient.data.model.album.Album

interface AlbumLocalDataSource {
    suspend fun getAlbumsFromDB():List<Album>
    suspend fun saveAlbumsToDB(album: List<Album>)
    suspend fun clearAll()
}