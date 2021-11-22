package com.arsh.lastfmclient.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.usecase.home.GetHomeUseCase

/**
 * ViewModel class for [HomeActivity]
 */
class HomeViewModel(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {
    fun getAlbums() = liveData {
        val albumDetails = getHomeUseCase.execute()
        emit(albumDetails)
    }

    suspend fun removeFromFavorites(album: Album) =
        getHomeUseCase.removeFromDb(album)

    suspend fun fetchFavoriteState(albumName: String): Boolean =
        getHomeUseCase.fetchFavoriteState(albumName)
}