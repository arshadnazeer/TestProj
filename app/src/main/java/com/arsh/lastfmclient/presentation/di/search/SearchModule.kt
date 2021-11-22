package com.arsh.lastfmclient.presentation.di.search

import com.arsh.lastfmclient.domain.usecase.search.GetArtistsUseCase
import com.arsh.lastfmclient.presentation.search.SearchArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @SearchScope
    @Provides
    fun providesSearchViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase
    ): SearchArtistViewModelFactory {
        return SearchArtistViewModelFactory(
            getArtistsUseCase
        )
    }
}