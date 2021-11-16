package com.arsh.lastfmclient.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.domain.usecase.search.GetArtistsUseCase

class SearchArtistViewModelFactory(
    private val getArtistsUseCase: GetArtistsUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchArtistViewModel(getArtistsUseCase) as T

    }
}