package com.arsh.lastfmclient.presentation.di

import com.arsh.lastfmclient.presentation.di.album.AlbumSubComponent

interface Injector {
    fun createAlbumSubComponent() : AlbumSubComponent
}