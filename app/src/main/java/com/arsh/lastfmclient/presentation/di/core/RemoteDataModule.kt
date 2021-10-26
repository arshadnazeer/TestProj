package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.data.api.FMClientService
import com.arsh.lastfmclient.data.repository.datasource.AlbumRemoteDataSource
import com.arsh.lastfmclient.data.repository.datasourceimpl.AlbumRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(
    private val method : String,
    private val artist : String,
    private val apikey: String
) {
    @Singleton
    @Provides
    fun providesRemoteDataSource(fmClientService: FMClientService) : AlbumRemoteDataSource{
        return AlbumRemoteDataSourceImpl(
            fmClientService,method,artist,apikey
        )
    }
}