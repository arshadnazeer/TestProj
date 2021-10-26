package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.repository.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.datasourceimpl.AlbumLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun providesLocalDataSource(albumDao: AlbumDao) : AlbumLocalDataSource{
        return AlbumLocalDataSourceImpl(albumDao)
    }
}