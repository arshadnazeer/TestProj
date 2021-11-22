package com.arsh.lastfmclient.domain.usecase.album

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository

/**
 * The GetAlbumsUseCase provides all the use cases
 * which are called from the ViewModel
 *
 * This layer acts as a communicating layer between ViewModels and the Repositories
 */
class GetAlbumsUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute(artistName: String): List<Album>? = albumRepository.getAlbums(artistName)
    suspend fun addToDb(album: Album): Unit = albumRepository.addToFavorites(album)
    suspend fun removeFromDb(album: Album): Unit = albumRepository.removeFromFavorites(album)
    suspend fun fetchFavoriteState(albumName: String): Boolean =
        albumRepository.fetchFavoriteState(albumName)
}
