package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.repository.AlbumRepositoryImpl
import com.arsh.lastfmclient.data.repository.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumRemoteDataSource: AlbumRemoteDataSource,
        albumLocalDataSource: AlbumLocalDataSource
    ) : AlbumRepository{

        return AlbumRepositoryImpl(
            albumRemoteDataSource, albumLocalDataSource
        )

    }
}