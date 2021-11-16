package com.arsh.lastfmclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.album.ImageTypeConverter

@Database(entities = [Album::class],version = 2,exportSchema = false)
@TypeConverters(ImageTypeConverter::class)
abstract class FMClientDatabase : RoomDatabase(){

    abstract fun albumDao(): AlbumDao

    val MIGRATION_1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER, PRIMARY KEY(`id`))")
        }
    }
}