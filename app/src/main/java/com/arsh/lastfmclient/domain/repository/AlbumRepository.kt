package com.arsh.lastfmclient.domain.repository

import com.arsh.lastfmclient.data.model.Album

interface AlbumRepository {
    suspend fun getAlbums() : List<Album>?
}