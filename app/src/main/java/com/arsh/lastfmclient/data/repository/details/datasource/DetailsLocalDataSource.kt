package com.arsh.lastfmclient.data.repository.details.datasource

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.details.Details
import retrofit2.Response

interface DetailsLocalDataSource {
    suspend fun getAlbums(albumName : String) : List<Album>
}