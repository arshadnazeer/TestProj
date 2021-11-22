package com.arsh.lastfmclient.domain.repository.album

import com.arsh.lastfmclient.data.model.album.Album

/**
 * This is the Album Repository interface,
 * which is implemented at the data layer in the class AlbumRepositoryImpl
 */
interface AlbumRepository {
    suspend fun getAlbums(artistName: String): List<Album>?
    suspend fun addToFavorites(album: Album): Unit
    suspend fun removeFromFavorites(album: Album): Unit
    suspend fun fetchFavoriteState(albumName: String): Boolean
    suspend fun getLocalAlbumList(): List<Album>
}