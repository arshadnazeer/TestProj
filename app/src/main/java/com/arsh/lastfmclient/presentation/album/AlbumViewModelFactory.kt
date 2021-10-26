package com.arsh.lastfmclient.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.domain.usecase.GetAlbumsUseCase

class AlbumViewModelFactory(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(getAlbumsUseCase) as T
    }
}