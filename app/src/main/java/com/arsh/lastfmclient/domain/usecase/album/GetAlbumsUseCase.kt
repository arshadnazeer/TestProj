package com.arsh.lastfmclient.domain.usecase.album

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository

class GetAlbumsUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute(artistName: String): List<Album>? = albumRepository.getAlbums(artistName)
    suspend fun addToDb(album: Album): Unit = albumRepository.addToFavorites(album)
    suspend fun removeFromDb(album: Album) : Unit = albumRepository.removeFromFavorites(album)
    suspend fun fetchFavoriteState(albumName: String): Boolean =
        albumRepository.fetchFavoriteState(albumName)
}
