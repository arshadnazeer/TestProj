package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.repository.album.AlbumRepositoryImpl
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.data.repository.search.SearchRepositoryImpl
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository
import com.arsh.lastfmclient.domain.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumRemoteDataSource: AlbumRemoteDataSource,
        albumLocalDataSource: AlbumLocalDataSource,
    ) : AlbumRepository {

        return AlbumRepositoryImpl(
            albumRemoteDataSource, albumLocalDataSource
        )

    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchRemoteDataSource: SearchRemoteDataSource
    ) : SearchRepository{
        return SearchRepositoryImpl(
            searchRemoteDataSource
        )
    }
}