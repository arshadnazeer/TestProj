package com.arsh.lastfmclient.presentation.di

import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import com.arsh.lastfmclient.presentation.di.details.DetailsSubComponent
import com.arsh.lastfmclient.presentation.di.home.HomeSubComponent
import com.arsh.lastfmclient.presentation.di.search.SearchSubComponent

interface Injector {
    fun createAlbumSubComponent(): AlbumSubComponent
    fun createSearchSubComponent(): SearchSubComponent
    fun createAlbumDetailsSubComponent(): DetailsSubComponent
    fun createHomeSubComponent(): HomeSubComponent
}