package com.arsh.lastfmclient.domain.usecase.home

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository

/**
 * The GetHomeUseCase provides all the use cases
 * which are called from the ViewModel
 *
 * This layer acts as a communicating layer between ViewModels and the Repositories
 */

class GetHomeUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute(): List<Album> =
        albumRepository.getLocalAlbumList()

    suspend fun fetchFavoriteState(albumName: String): Boolean =
        albumRepository.fetchFavoriteState(albumName)

    suspend fun removeFromDb(album: Album): Unit =
        albumRepository.removeFromFavorites(album)
}