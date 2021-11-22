package com.arsh.lastfmclient.presentation.di.album

import com.arsh.lastfmclient.presentation.album.AlbumActivity
import dagger.Subcomponent

@AlbumScope
@Subcomponent(modules = [AlbumModule::class])
interface AlbumSubComponent {
    fun inject(albumActivity: AlbumActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AlbumSubComponent
    }
}