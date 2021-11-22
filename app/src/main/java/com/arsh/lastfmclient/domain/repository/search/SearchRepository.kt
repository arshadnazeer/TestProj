package com.arsh.lastfmclient.domain.repository.search

import com.arsh.lastfmclient.data.model.search.Artists

/**
 * This is the Search Repository interface,
 * which is implemented at the data layer in the class SearchRepositoryImpl
 */
interface SearchRepository {
    suspend fun getArtists(artist: String): List<Artists>?
}