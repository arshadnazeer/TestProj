package com.arsh.lastfmclient.data.repository.album.datasource

import com.arsh.lastfmclient.data.model.album.Album

/**
 * Interface with functions performing operations to interact with the database locally
 */
interface AlbumLocalDataSource {
    suspend fun getAlbumsFromDB(): List<Album>
    suspend fun saveAlbumsToDB(album: List<Album>)
    suspend fun removeAlbumsFromDB(album: Album)
    suspend fun fetchFavoriteState(albumName: String): Boolean
    suspend fun clearAll()
}