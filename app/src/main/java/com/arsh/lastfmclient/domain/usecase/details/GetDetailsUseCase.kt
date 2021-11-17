package com.arsh.lastfmclient.domain.usecase.details

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.details.AlbumDetails
import com.arsh.lastfmclient.domain.repository.details.DetailsRepository

class GetDetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend fun execute(albumName: String, artist: String): AlbumDetails =
        detailsRepository.getDetails(albumName, artist)

    suspend fun fetchAlbumDetails(albumName: String) : List<Album> =
        detailsRepository.fetchAlbums(albumName)
}