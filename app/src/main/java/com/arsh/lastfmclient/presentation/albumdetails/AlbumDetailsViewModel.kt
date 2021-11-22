package com.arsh.lastfmclient.presentation.albumdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.domain.usecase.details.GetDetailsUseCase

/**
 * ViewModel class for [AlbumDetailActivity]
 */
class AlbumDetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {
    fun getAlbumDetails(albumName: String, artist: String) = liveData {
        val albumDetails = getDetailsUseCase.execute(albumName, artist)
        emit(albumDetails)
    }

    suspend fun getLocalAlbumDetails(albumName: String) =
        getDetailsUseCase.fetchAlbumDetails(albumName)
}