package com.arsh.lastfmclient.presentation.di.search

import com.arsh.lastfmclient.presentation.album.AlbumActivity
import com.arsh.lastfmclient.presentation.search.SearchArtistActivity
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchSubComponent {
    fun inject(searchArtistActivity: SearchArtistActivity)

    @Subcomponent.Factory
    interface Factory{
            fun create() : SearchSubComponent
        }
}