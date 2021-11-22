package com.arsh.lastfmclient.domain.repository.details

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.details.AlbumDetails

/**
 * This is the Album Detail Repository interface,
 * which is implemented at the data layer in the class DetailsRepositoryImpl
 */
interface DetailsRepository {
    suspend fun getDetails(album: String, artist: String): AlbumDetails
    suspend fun fetchAlbums(albumName: String): List<Album>
}