package com.arsh.lastfmclient.presentation.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.usecase.album.GetAlbumsUseCase

class AlbumViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    fun getAlbums(artistName : String) = liveData {
        emit(getAlbumsUseCase.execute(artistName))
    }

    suspend fun removeFromFavorites(album : Album) =
        getAlbumsUseCase.removeFromDb(album)

    suspend fun addToFavorites(album : Album) =
        getAlbumsUseCase.addToDb(album)

    suspend fun fetchFavoriteState(albumName : String) : Boolean =
        getAlbumsUseCase.fetchFavoriteState(albumName)

}