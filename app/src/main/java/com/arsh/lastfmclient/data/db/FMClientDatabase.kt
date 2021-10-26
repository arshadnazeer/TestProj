package com.arsh.lastfmclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arsh.lastfmclient.data.model.Album

@Database(entities = [Album::class],version = 1,exportSchema = false)
abstract class FMClientDatabase : RoomDatabase(){

    abstract fun albumDao(): AlbumDao
}