package com.arsh.lastfmclient.data.repository.details.datasourceimpl

import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsLocalDataSource

class DetailsLocalDataSourceImpl(
    private val albumDao: AlbumDao
) : DetailsLocalDataSource {
    override suspend fun getAlbums(albumName: String): List<Album> {
        return albumDao.getAlbums()
    }
}