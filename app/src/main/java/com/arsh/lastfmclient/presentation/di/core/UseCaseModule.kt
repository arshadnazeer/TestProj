package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.domain.repository.album.AlbumRepository
import com.arsh.lastfmclient.domain.repository.search.SearchRepository
import com.arsh.lastfmclient.domain.usecase.album.GetAlbumsUseCase
import com.arsh.lastfmclient.domain.usecase.search.GetArtistsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun providesGetAlbumUseCase(albumRepository: AlbumRepository): GetAlbumsUseCase {
        return GetAlbumsUseCase(albumRepository)
    }

    @Provides
    fun providesGetArtistUseCase(searchRepository: SearchRepository): GetArtistsUseCase {
        return GetArtistsUseCase(searchRepository)
    }
}