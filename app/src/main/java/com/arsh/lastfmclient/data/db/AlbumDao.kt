package com.arsh.lastfmclient.data.db

import androidx.room.*
import com.arsh.lastfmclient.data.model.album.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbums(albums: List<Album>)

    @Query("DELETE FROM top_albums")
    suspend fun deleteAllAlbums()

    @Delete
    suspend fun deleteAlbum(album : Album)

    @Query("SELECT * FROM top_albums")
    suspend fun getAlbums(): List<Album>

    @Query("SELECT * FROM top_albums WHERE name= :albumName")
    suspend fun fetchFavoriteState(albumName: String): List<Album>
}