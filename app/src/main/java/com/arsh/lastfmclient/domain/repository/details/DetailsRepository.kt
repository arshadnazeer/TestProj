package com.arsh.lastfmclient.domain.repository.details

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.details.AlbumDetails

interface DetailsRepository {
    suspend fun getDetails(album : String, artist : String) : AlbumDetails
    suspend fun fetchAlbums(albumName : String) : List<Album>
}