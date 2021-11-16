package com.arsh.lastfmclient.presentation.di

import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent
import com.arsh.lastfmclient.presentation.di.search.SearchSubComponent

interface Injector {
    fun createAlbumSubComponent() : AlbumSubComponent
    fun createSearchSubComponent() : SearchSubComponent
}