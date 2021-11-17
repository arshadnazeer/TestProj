package com.arsh.lastfmclient.presentation.di.home

import com.arsh.lastfmclient.domain.usecase.home.GetHomeUseCase
import com.arsh.lastfmclient.presentation.di.search.SearchScope
import com.arsh.lastfmclient.presentation.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @HomeScope
    @Provides
    fun providesHomeViewModelFactory(
        getHomeUseCase: GetHomeUseCase
    ) : HomeViewModelFactory {
        return HomeViewModelFactory(
            getHomeUseCase
        )
    }
}