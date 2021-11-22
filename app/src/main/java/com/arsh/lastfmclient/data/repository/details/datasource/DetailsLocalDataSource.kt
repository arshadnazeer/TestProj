package com.arsh.lastfmclient.data.repository.details.datasource

import com.arsh.lastfmclient.data.model.album.Album

/**
 * Interface with functions performing operations to interact with the database locally
 */
interface DetailsLocalDataSource {
    suspend fun getAlbums(albumName: String): List<Album>
}