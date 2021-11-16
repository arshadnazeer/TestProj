package com.arsh.lastfmclient.domain.usecase.search

import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.domain.repository.search.SearchRepository

class GetArtistsUseCase (private val searchRepository: SearchRepository) {
    suspend fun execute(artist : String) : List<Artists>? = searchRepository.getArtists(artist)

}