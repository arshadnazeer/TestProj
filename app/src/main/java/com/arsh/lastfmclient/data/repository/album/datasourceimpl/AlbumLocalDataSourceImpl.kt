package com.arsh.lastfmclient.data.repository.album.datasourceimpl

import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Class implementing the [AlbumLocalDataSource] interface
 */

class AlbumLocalDataSourceImpl(private val albumDao: AlbumDao) : AlbumLocalDataSource {
    override suspend fun getAlbumsFromDB(): List<Album> = albumDao.getAlbums()

    override suspend fun saveAlbumsToDB(album: List<Album>) {
        CoroutineScope(Dispatchers.IO).launch {
            albumDao.saveAlbums(album)
        }
    }

    override suspend fun removeAlbumsFromDB(album: Album) {
        CoroutineScope(Dispatchers.IO).launch {
            albumDao.deleteAlbum(album)
        }
    }

    override suspend fun fetchFavoriteState(albumName: String): Boolean =
        albumDao.fetchFavoriteState(albumName).isNotEmpty()


    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            albumDao.deleteAllAlbums()
        }
    }
}