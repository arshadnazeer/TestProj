package com.arsh.lastfmclient.presentation

import android.app.Application
import com.arsh.lastfmclient.BuildConfig
import com.arsh.lastfmclient.presentation.di.Injector
import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import com.arsh.lastfmclient.presentation.di.core.*
import com.arsh.lastfmclient.presentation.di.search.SearchSubComponent

class App : Application(),Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.method, BuildConfig.artist,BuildConfig.API_KEY,BuildConfig.format))
            .build()
    }

    override fun createAlbumSubComponent(): AlbumSubComponent {
        return appComponent.albumSubComponent().create()
    }

    override fun createSearchSubComponent(): SearchSubComponent {
        return appComponent.searchSubComponent().create()
    }
}