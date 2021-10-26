package com.arsh.lastfmclient.data.repository.datasourceimpl

import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.model.Album
import com.arsh.lastfmclient.data.repository.datasource.AlbumLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumLocalDataSourceImpl(private val albumDao: AlbumDao) : AlbumLocalDataSource {
    override suspend fun getAlbumsFromDB(): List<Album> = albumDao.getAlbums()

    override suspend fun saveAlbumsToDB(album: List<Album>) {
        CoroutineScope(Dispatchers.IO).launch {
            albumDao.saveAlbums(album)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            albumDao.deleteAllAlbums()
        }
    }
}