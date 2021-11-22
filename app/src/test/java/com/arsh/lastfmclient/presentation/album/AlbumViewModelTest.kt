package com.arsh.lastfmclient.presentation.album

import androidx.lifecycle.Observer
import com.arsh.lastfmclient.BaseJUnitTest
import com.arsh.lastfmclient.TestCoroutineRule
import com.arsh.lastfmclient.data.model.album.*
import com.arsh.lastfmclient.domain.usecase.album.GetAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import retrofit2.Response
import java.util.*

@ExperimentalCoroutinesApi
class AlbumViewModelTest : BaseJUnitTest() {
    private val getAlbumUseCase = mock<GetAlbumsUseCase>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val albumViewModel by lazy {
        AlbumViewModel(getAlbumUseCase)
    }

    private val albumObserver = mock<Observer<List<Album>?>>()

    @Test
    fun `getAlbums should return non empty list from data source`() {

        val artistName = "beatles"
        val album = Album(
            "1221", "beatles", 2, "121",
            arrayListOf(Image("", "")),
            "Jackson"
        )
        val albumList = listOf(
            album
        )

        testCoroutineRule.runBlockingTest {
            doReturn(albumList)
                .`when`(getAlbumUseCase).execute(artistName)

            albumViewModel.albumLiveData.observeForever(albumObserver)

            albumViewModel.getAlbums(artistName)
            verify(albumObserver).onChanged(albumList)
            verify(getAlbumUseCase).execute(artistName)
        }
    }

    @Test
    fun `when the network call fails throw exception`() {
        val throwable = Throwable()
        val artistName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(getAlbumUseCase).execute(artistName)

                albumViewModel.getAlbums(artistName)

                verify(getAlbumUseCase).execute(artistName)
            }
        }

    }

    @Test
    fun `fetchFavoriteState function to return boolean`(){
        val albumName = "beatles"

        testCoroutineRule.runBlockingTest {
            doReturn(false)
                .`when`(getAlbumUseCase).fetchFavoriteState(albumName)

            albumViewModel.fetchFavoriteState(albumName)

            verify(getAlbumUseCase).fetchFavoriteState(albumName)
        }
    }

    @Test
    fun `when the fetchFavoriteState fails throw exception`() {
        val throwable = Throwable()
        val albumName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(getAlbumUseCase).fetchFavoriteState(albumName)

                albumViewModel.fetchFavoriteState(albumName)

                verify(getAlbumUseCase).fetchFavoriteState(albumName)
            }
        }

    }

    override fun start() {
    }

    override fun stop() {
        Mockito.verifyNoMoreInteractions(getAlbumUseCase)
        Mockito.verifyNoMoreInteractions(albumObserver)
    }


}