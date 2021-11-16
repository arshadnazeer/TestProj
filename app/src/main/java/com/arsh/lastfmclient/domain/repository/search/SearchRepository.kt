package com.arsh.lastfmclient.domain.repository.search

import com.arsh.lastfmclient.data.model.search.Artists

interface SearchRepository {
    suspend fun getArtists(artist : String) : List<Artists>?
}