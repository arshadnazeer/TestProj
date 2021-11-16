package com.arsh.lastfmclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arsh.lastfmclient.data.model.album.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbums(albums: List<Album>)

    @Query("DELETE FROM top_albums")
    suspend fun deleteAllAlbums()

    @Query("SELECT * FROM top_albums")
    suspend fun getAlbums(): List<Album>
}