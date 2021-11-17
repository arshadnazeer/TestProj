package com.arsh.lastfmclient.data.repository.album

import android.util.Log
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository
import java.lang.Exception

class AlbumRepositoryImpl(
    private val albumRemoteDataSource: AlbumRemoteDataSource,
    private val albumLocalDataSource: AlbumLocalDataSource
) : AlbumRepository {
    override suspend fun getAlbums(artistName : String): List<Album> {
        return getAlbumsFromAPI(artistName)
    }

    override suspend fun addToFavorites(album: Album) {
        albumLocalDataSource.saveAlbumsToDB(listOf(album))
    }

    override suspend fun removeFromFavorites(album: Album) {
        albumLocalDataSource.removeAlbumsFromDB(album)
    }

    override suspend fun fetchFavoriteState(albumName: String): Boolean {
        return albumLocalDataSource.fetchFavoriteState(albumName)
    }

    override suspend fun getLocalAlbumList(): List<Album> {
        return albumLocalDataSource.getAlbumsFromDB()
    }

    private suspend fun getAlbumsFromAPI(artistName: String):List<Album>{
        lateinit var albumList: List<Album>
        try {
            val response = albumRemoteDataSource.getAlbums(artistName)
            val body = response.body()
            if (body!=null)
                albumList = body.topalbums.album

        } catch (e: Exception){
            Log.e("TAG",e.message.toString())
        }
        return albumList
    }

//    private suspend fun getAlbumsFromDB(artistName: String):List<Album>{
//        lateinit var albumList: List<Album>
//        try {
//            albumList = albumLocalDataSource.getAlbumsFromDB()
//
//        } catch (e: Exception){
//            Log.e("TAG",e.message.toString())
//        }
//        if (albumList.isEmpty()){
//            albumList = getAlbumsFromAPI(artistName)
//            albumLocalDataSource.saveAlbumsToDB(albumList)
//        }
//        return albumList
//    }
}