package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.data.repository.album.datasourceimpl.AlbumRemoteDataSourceImpl
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import com.arsh.lastfmclient.data.repository.search.datasourceimpl.SearchRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(
    private val method : String,
    private val artist : String,
    private val apikey: String,
    private val format: String,
) {
    @Singleton
    @Provides
    fun providesRemoteDataSource(fmClientService: FMClientService) : AlbumRemoteDataSource{
        return AlbumRemoteDataSourceImpl(
            fmClientService,method,artist,apikey,format
        )
    }

    @Singleton
    @Provides
    fun providesSearchRemoteDataSource(fmClientService: FMClientService) : SearchRemoteDataSource{
        return SearchRemoteDataSourceImpl(
            fmClientService,method,apikey,format
        )
    }
}