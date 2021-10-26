package com.arsh.lastfmclient.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.domain.usecase.GetAlbumsUseCase

class AlbumViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    fun getAlbums() = liveData {
        val albumlist = getAlbumsUseCase.execute()
        emit(albumlist)
    }
}