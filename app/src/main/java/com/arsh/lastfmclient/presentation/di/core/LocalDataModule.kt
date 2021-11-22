package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.db.AlbumDao
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.album.datasourceimpl.AlbumLocalDataSourceImpl
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsLocalDataSource
import com.arsh.lastfmclient.data.repository.details.datasourceimpl.DetailsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module for Local Data source
 */
@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun providesLocalDataSource(albumDao: AlbumDao): AlbumLocalDataSource {
        return AlbumLocalDataSourceImpl(albumDao)
    }

    @Singleton
    @Provides
    fun providesDetailsLocalDataSource(albumDao: AlbumDao): DetailsLocalDataSource {
        return DetailsLocalDataSourceImpl(albumDao)
    }
}