package com.arsh.lastfmclient.presentation.di.core

import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
DatabaseModule::class,
RemoteDataModule::class,
LocalDataModule::class,
NetModule::class,
RepositoryModule::class,
UseCaseModule::class
])
interface AppComponent {

    fun albumSubComponent():AlbumSubComponent.Factory
}