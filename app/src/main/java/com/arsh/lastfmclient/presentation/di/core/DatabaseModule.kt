package com.arsh.lastfmclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.db.FMClientDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(context: Context):FMClientDatabase{
        return Room.databaseBuilder(context,FMClientDatabase::class.java,"fmclient")
            .build()
    }

    @Singleton
    @Provides
    fun provideAlbumDAO(fmClientDatabase: FMClientDatabase) : AlbumDao{
        return fmClientDatabase.albumDao()
    }
}