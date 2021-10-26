package com.arsh.lastfmclient.presentation.di.core

import android.content.Context
import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [AlbumSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesApplicationContext():Context{
        return context.applicationContext
    }
}