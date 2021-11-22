package com.arsh.lastfmclient.presentation.albumdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.domain.usecase.details.GetDetailsUseCase

/**
 * Factory class for [AlbumDetailsViewModel]
 */
class AlbumDetailsViewModelFactory(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumDetailsViewModel(getDetailsUseCase) as T

    }
}