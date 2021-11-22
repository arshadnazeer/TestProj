package com.arsh.lastfmclient.domain.usecase.search

import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.domain.repository.search.SearchRepository

/**
 * The GetArtistsUseCase provides all the use cases
 * which are called from the ViewModel
 *
 * This layer acts as a communicating layer between ViewModels and the Repositories
 */
class GetArtistsUseCase(private val searchRepository: SearchRepository) {
    suspend fun execute(artist: String): List<Artists>? = searchRepository.getArtists(artist)

}