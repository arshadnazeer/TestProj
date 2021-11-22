package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.domain.repository.album.AlbumRepository
import com.arsh.lastfmclient.domain.repository.details.DetailsRepository
import com.arsh.lastfmclient.domain.repository.search.SearchRepository
import com.arsh.lastfmclient.domain.usecase.album.GetAlbumsUseCase
import com.arsh.lastfmclient.domain.usecase.details.GetDetailsUseCase
import com.arsh.lastfmclient.domain.usecase.home.GetHomeUseCase
import com.arsh.lastfmclient.domain.usecase.search.GetArtistsUseCase
import dagger.Module
import dagger.Provides

/**
 * Module for UseCases
 */
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

    @Provides
    fun providesGetArtistDetailsUseCase(albumDetailsRepository: DetailsRepository): GetDetailsUseCase {
        return GetDetailsUseCase(albumDetailsRepository)
    }

    @Provides
    fun providesHomeUseCase(albumRepository: AlbumRepository): GetHomeUseCase {
        return GetHomeUseCase(albumRepository)
    }
}