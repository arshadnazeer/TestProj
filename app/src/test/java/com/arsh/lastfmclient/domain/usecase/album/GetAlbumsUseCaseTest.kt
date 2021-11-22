package com.arsh.lastfmclient.domain.usecase.album

import com.arsh.lastfmclient.TestCoroutineRule
import com.arsh.lastfmclient.data.model.album.*
import com.arsh.lastfmclient.domain.repository.album.AlbumRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetAlbumsUseCaseTest {
    private val albumRepository = mock<AlbumRepository>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getAlbumsUseCase by lazy {
        GetAlbumsUseCase(albumRepository)
    }

    @Test
    fun `execute function to return non empty list`() {
        val artistName = "beatles"
        val albumList = listOf(
            Album(
                "1221", "beatles", 2, "121",
                arrayListOf(Image("", "")),
                "Jackson"
            )
        )

        val albums = Albums(Topalbums(albumList, Attr("", "", "", "", "")))

        testCoroutineRule.runBlockingTest {
            doReturn(albums)
                .`when`(albumRepository).getAlbums(artistName)

            getAlbumsUseCase.execute(artistName)

            verify(albumRepository).getAlbums(artistName)
        }
    }

    @Test
    fun `when the execute fails throw exception`() {
        val throwable = Throwable()
        val artistName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(albumRepository).getAlbums(artistName)

                getAlbumsUseCase.execute(artistName)

                verify(albumRepository).getAlbums(artistName)
            }
        }

    }

    @Test
    fun `fetchFavoriteState function to return boolean`() {
        val albumName = "beatles"

        testCoroutineRule.runBlockingTest {
            doReturn(false)
                .`when`(albumRepository).fetchFavoriteState(albumName)

            getAlbumsUseCase.fetchFavoriteState(albumName)

            verify(albumRepository).fetchFavoriteState(albumName)
        }
    }

    @Test
    fun `when the fetchFavoriteState fails throw exception`() {
        val throwable = Throwable()
        val albumName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(albumRepository).fetchFavoriteState(albumName)

                getAlbumsUseCase.fetchFavoriteState(albumName)

                verify(albumRepository).fetchFavoriteState(albumName)
            }
        }

    }

}