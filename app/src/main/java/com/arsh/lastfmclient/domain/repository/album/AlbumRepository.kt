package com.arsh.lastfmclient.domain.repository.album

import com.arsh.lastfmclient.data.model.album.Album

interface AlbumRepository {
    suspend fun getAlbums() : List<Album>?
}