package com.arsh.lastfmclient.presentation.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.domain.usecase.album.GetAlbumsUseCase

/**
 * ViewModel class for [AlbumActivity]
 */
class AlbumViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val _albumsLiveData : MutableLiveData<List<Album>?> = MutableLiveData()

    val albumLiveData = _albumsLiveData

    suspend fun getAlbums(artistName: String) {
        _albumsLiveData.value = getAlbumsUseCase.execute(artistName)
    }

    suspend fun removeFromFavorites(album: Album) =
        getAlbumsUseCase.removeFromDb(album)

    suspend fun addToFavorites(album: Album) =
        getAlbumsUseCase.addToDb(album)

    suspend fun fetchFavoriteState(albumName: String): Boolean =
        getAlbumsUseCase.fetchFavoriteState(albumName)

}