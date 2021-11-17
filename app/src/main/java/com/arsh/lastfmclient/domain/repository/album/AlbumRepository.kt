package com.arsh.lastfmclient.domain.repository.album

import com.arsh.lastfmclient.data.model.album.Album

interface AlbumRepository {
    suspend fun getAlbums(artistName : String) : List<Album>?
    suspend fun addToFavorites(album: Album) : Unit
    suspend fun removeFromFavorites(album: Album) : Unit
    suspend fun fetchFavoriteState(albumName : String) : Boolean
    suspend fun getLocalAlbumList() : List<Album>
}