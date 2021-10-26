package com.arsh.lastfmclient.presentation.di.album

import com.arsh.lastfmclient.domain.usecase.GetAlbumsUseCase
import com.arsh.lastfmclient.presentation.album.AlbumViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AlbumModule {
    @AlbumScope
    @Provides
    fun providesAlbumViewModelFactory(
        getAlbumsUseCase: GetAlbumsUseCase
    ) : AlbumViewModelFactory{
        return AlbumViewModelFactory(
            getAlbumsUseCase
        )
    }
}