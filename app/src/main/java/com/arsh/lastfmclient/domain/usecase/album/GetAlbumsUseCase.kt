package com.arsh.lastfmclient.domain.usecase.album

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository

class GetAlbumsUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute() : List<Album>? = albumRepository.getAlbums()
}
