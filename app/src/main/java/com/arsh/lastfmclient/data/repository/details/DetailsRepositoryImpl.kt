package com.arsh.lastfmclient.data.repository.details

import android.util.Log
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.details.AlbumDetails
import com.arsh.lastfmclient.data.model.details.Artist
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsLocalDataSource
import com.arsh.lastfmclient.data.repository.details.datasource.DetailsRemoteDataSource
import com.arsh.lastfmclient.domain.repository.details.DetailsRepository

/**
 * This is the implementation class of the Details Repository Interface present in the domain layer
 *The Class is injected with dependencies for local datasource and remote datasource
 */
class DetailsRepositoryImpl(
    private val detailsRemoteDataSource: DetailsRemoteDataSource,
    private val detailsLocalDataSource: DetailsLocalDataSource
) : DetailsRepository {
    override suspend fun getDetails(album: String, artist: String): AlbumDetails {
        return getDetailsFromAPI(album,artist)
    }

    override suspend fun fetchAlbums(albumName: String): List<Album> {
        return detailsLocalDataSource.getAlbums(albumName)
    }

    private suspend fun getDetailsFromAPI(album : String, artist: String): AlbumDetails {
        lateinit var albumDetailsList: AlbumDetails
        try {
            val response = detailsRemoteDataSource.getAlbumDetails(album,artist)
            val body = response.body()
            if (body!=null)
                albumDetailsList= body.albumDetails

        } catch (e: Exception){
            Log.e("TAG",e.message.toString())
        }
        return albumDetailsList
    }
}