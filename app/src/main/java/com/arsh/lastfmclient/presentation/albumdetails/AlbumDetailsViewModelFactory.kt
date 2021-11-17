package com.arsh.lastfmclient.presentation.albumdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.domain.usecase.details.GetDetailsUseCase
import com.arsh.lastfmclient.presentation.album.AlbumViewModel

class AlbumDetailsViewModelFactory(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(getDetailsUseCase) as T

    }
}