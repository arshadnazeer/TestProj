package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import com.arsh.lastfmclient.presentation.di.details.DetailsSubComponent
import com.arsh.lastfmclient.presentation.di.home.HomeSubComponent
import com.arsh.lastfmclient.presentation.di.search.SearchSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        RemoteDataModule::class,
        LocalDataModule::class,
        NetModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    fun albumSubComponent(): AlbumSubComponent.Factory

    fun searchSubComponent(): SearchSubComponent.Factory

    fun detailsSubComponent(): DetailsSubComponent.Factory

    fun homeSubComponent(): HomeSubComponent.Factory


}