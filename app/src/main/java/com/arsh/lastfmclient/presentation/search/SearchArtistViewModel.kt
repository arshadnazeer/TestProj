package com.arsh.lastfmclient.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.domain.usecase.search.GetArtistsUseCase

class SearchArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase
) : ViewModel() {

    fun getArtists(artist : String) = liveData {
        val artistList = getArtistsUseCase.execute(artist)
        emit(artistList)
    }
}