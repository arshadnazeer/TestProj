package com.arsh.lastfmclient.domain.usecase

import com.arsh.lastfmclient.data.model.Album
import com.arsh.lastfmclient.domain.repository.AlbumRepository

class GetAlbumsUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute() : List<Album>? = albumRepository.getAlbums()
}
