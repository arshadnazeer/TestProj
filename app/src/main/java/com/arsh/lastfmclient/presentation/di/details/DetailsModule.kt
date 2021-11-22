package com.arsh.lastfmclient.presentation.di.details

import com.arsh.lastfmclient.domain.usecase.details.GetDetailsUseCase
import com.arsh.lastfmclient.presentation.albumdetails.AlbumDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @DetailsScope
    @Provides
    fun providesAlbumDetailsViewModelFactory(
        getAlbumsDetailsUseCase: GetDetailsUseCase
    ): AlbumDetailsViewModelFactory {
        return AlbumDetailsViewModelFactory(
            getAlbumsDetailsUseCase
        )
    }
}