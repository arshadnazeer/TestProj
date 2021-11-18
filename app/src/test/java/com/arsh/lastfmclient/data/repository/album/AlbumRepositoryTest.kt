package com.arsh.lastfmclient.data.repository.album

import com.arsh.lastfmclient.TestCoroutineRule
import com.arsh.lastfmclient.data.model.album.*
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumLocalDataSource
import com.arsh.lastfmclient.data.repository.album.datasource.AlbumRemoteDataSource
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
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class AlbumRepositoryTest {
    private val albumRemoteDataSource = mock<AlbumRemoteDataSource>()
    private val albumLocalDataSource = mock<AlbumLocalDataSource>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val albumRepository by lazy {
        AlbumRepositoryImpl(albumRemoteDataSource, albumLocalDataSource)
    }

    @Test
    fun `when the network call is successful return list of albums response`() {

        val artistName = "beatles"
        val albumList = listOf(
            Album(
                "1221", "beatles", 2, "121",
                arrayListOf(Image("", ""))
            )
        )

        val albums = Albums(Topalbums(albumList,Attr("","","","","")))


        testCoroutineRule.runBlockingTest {
            doReturn(Response.success(albums))
                .`when`(albumRemoteDataSource).getAlbums(artistName)

            albumRepository.getAlbums(artistName)

            verify(albumRemoteDataSource).getAlbums(artistName)
        }
    }

    @Test
    fun `when the network call fails throw exception`() {
        val throwable = Throwable()
        val artistName = "beatles"
        Assert.assertThrows(Throwable::class.java) {
            testCoroutineRule.runBlockingTest {
                doThrow(throwable).`when`(albumRemoteDataSource).getAlbums(artistName)

                albumRepository.getAlbums(artistName)

                verify(albumRemoteDataSource).getAlbums(artistName)
            }
        }

    }



}