package com.arsh.lastfmclient.presentation.di.core

import android.content.Context
import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import com.arsh.lastfmclient.presentation.di.details.DetailsSubComponent
import com.arsh.lastfmclient.presentation.di.home.HomeSubComponent
import com.arsh.lastfmclient.presentation.di.search.SearchSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [AlbumSubComponent::class, SearchSubComponent::class, DetailsSubComponent::class, HomeSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesApplicationContext(): Context {
        return context.applicationContext
    }
}