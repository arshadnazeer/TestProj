package com.arsh.lastfmclient.data.repository

import android.util.Log
import com.arsh.lastfmclient.data.model.Album
import com.arsh.lastfmclient.data.repository.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.domain.repository.AlbumRepository
import java.lang.Exception

class AlbumRepositoryImpl(
    private val albumRemoteDataSource: AlbumRemoteDataSource,
    private val albumLocalDataSource: AlbumLocalDataSource
) : AlbumRepository {
    override suspend fun getAlbums(): List<Album>? {
        return getAlbumsFromDB()
    }

    private suspend fun getAlbumsFromAPI():List<Album>{
        lateinit var albumList: List<Album>
        try {
            val response = albumRemoteDataSource.getAlbums()
            val body = response.body()
            if (body!=null)
                albumList = body.album

        } catch (e: Exception){
            Log.e("TAG",e.message.toString())
        }
        return albumList
    }

    private suspend fun getAlbumsFromDB():List<Album>{
        lateinit var albumList: List<Album>
        try {
            albumList = albumLocalDataSource.getAlbumsFromDB()

        } catch (e: Exception){
            Log.e("TAG",e.message.toString())
        }
        if (albumList.isNotEmpty()){
            albumList = getAlbumsFromAPI()
            albumLocalDataSource.saveAlbumsToDB(albumList)
        }
        return albumList
    }
}