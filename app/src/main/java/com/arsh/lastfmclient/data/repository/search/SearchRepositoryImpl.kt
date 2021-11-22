package com.arsh.lastfmclient.data.repository.search

import android.util.Log
import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import com.arsh.lastfmclient.domain.repository.search.SearchRepository

/**
 * This is the implementation class of the Search Repository Interface present in the domain layer
 *The Class is injected with dependencies for remote datasource
 */
class SearchRepositoryImpl(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getArtists(artist: String): List<Artists>? {
        return getArtistsFromAPI(artist)
    }

    private suspend fun getArtistsFromAPI(artist: String): List<Artists> {
        lateinit var artistList: List<Artists>
        try {
            val response = searchRemoteDataSource.getArtists(artist)
            val body = response.body()
            if (body != null)
                artistList = body.results?.artistmatches?.artist ?: emptyList()

        } catch (e: Exception) {
//            Log.e("TAG", e.message.toString())
            e.printStackTrace()
        }
        return artistList
    }
}