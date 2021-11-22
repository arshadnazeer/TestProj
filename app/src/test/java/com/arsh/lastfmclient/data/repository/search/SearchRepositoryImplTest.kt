package com.arsh.lastfmclient.data.repository.search

import com.arsh.lastfmclient.TestCoroutineRule
import com.arsh.lastfmclient.data.model.album.*
import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.data.model.search.SearchResult
import com.arsh.lastfmclient.data.repository.search.datasource.SearchRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class SearchRepositoryImplTest {
    private val searchRemoteDataSource = mock<SearchRemoteDataSource>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val searchRepository by lazy {
        SearchRepositoryImpl(searchRemoteDataSource)
    }

    @Test
    fun `when the network call is successful return SearchResult response`() {
        val artistName = "Jackson"
        val searchResult = SearchResult(null)

        testCoroutineRule.runBlockingTest {
            doReturn(Response.success(searchResult))
                .`when`(searchRemoteDataSource).getArtists(artistName)

            searchRepository.getArtists(artistName)

            verify(searchRemoteDataSource).getArtists(artistName)
        }
    }

    @Test
    fun `when the network call fails throw exception`() {
        val throwable = Throwable()
        val artistName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(searchRemoteDataSource).getArtists(artistName)

                searchRepository.getArtists(artistName)

                verify(searchRemoteDataSource).getArtists(artistName)
            }
        }

    }
}
