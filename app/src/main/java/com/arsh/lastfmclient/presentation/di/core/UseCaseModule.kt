package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.domain.repository.AlbumRepository
import com.arsh.lastfmclient.domain.usecase.GetAlbumsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun providesGetAlbumUseCase(albumRepository: AlbumRepository):GetAlbumsUseCase{
        return GetAlbumsUseCase(albumRepository)
    }
}