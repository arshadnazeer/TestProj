package com.arsh.lastfmclient.presentation.di.details

import com.arsh.lastfmclient.presentation.albumdetails.AlbumDetailActivity
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsSubComponent {
    fun inject(albumDetailsActivity: AlbumDetailActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsSubComponent
    }
}