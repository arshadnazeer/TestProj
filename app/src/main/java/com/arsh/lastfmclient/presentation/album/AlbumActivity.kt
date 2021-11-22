package com.arsh.lastfmclient.presentation.album

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.arsh.lastfmclient.databinding.ActivityAlbumBinding
import com.arsh.lastfmclient.presentation.albumdetails.ALBUM_NAME
import com.arsh.lastfmclient.presentation.albumdetails.AlbumDetailActivity
import com.arsh.lastfmclient.presentation.di.Injector
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Album Activity represents the list of all the albums
 */

const val ARTIST_NAME = "artist_name"
const val IS_MAIN = "is_main"

class AlbumActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumViewModelFactory
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var binding: ActivityAlbumBinding
    private lateinit var adapter: AlbumAdapter

    private val artistName: String by lazy {
        intent.extras?.get(ARTIST_NAME).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createAlbumSubComponent()
            .inject(this)

        albumViewModel = ViewModelProvider(this, factory)[AlbumViewModel::class.java]
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.albumRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = AlbumAdapter(object : AlbumItemContract {
            override fun favPos(pos: Int) {
                albumViewModel.viewModelScope.launch {
                    adapter.getList()[pos].name?.let {
                        if (albumViewModel.fetchFavoriteState(it))
                            albumViewModel.removeFromFavorites(adapter.getList()[pos])
                        else {
                            val album = adapter.getList()[pos]
                            album.artistName = artistName
                            albumViewModel.addToFavorites(album)
                        }
                    }

                    runOnUiThread {
                        adapter.notifyItemChanged(pos)
                    }
                }
            }

            override fun clickedPos(pos: Int) {
                val intent = Intent(
                    this@AlbumActivity, AlbumDetailActivity::class.java
                )
                intent.putExtra(ALBUM_NAME, adapter.getList()[pos].name)
                intent.putExtra(ARTIST_NAME, artistName)
                intent.putExtra(IS_MAIN,false)
                startActivity(intent)
            }

            override fun localFavoriteState(albumName: String): Boolean {
                var fav: Boolean
                runBlocking {
                    val favAsync = async {
                        albumViewModel.fetchFavoriteState(albumName)
                    }
                    fav = favAsync.await()
                }

                return fav
            }

        })
        binding.albumRecyclerView.adapter = adapter
        displayPopularAlbums()
    }

    private fun displayPopularAlbums() {
        binding.albumProgressBar.visibility = View.VISIBLE

        albumViewModel.albumLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.albumProgressBar.visibility = View.GONE
            } else {
                binding.albumProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No Data Available", Toast.LENGTH_LONG).show()
            }
        })

        albumViewModel.viewModelScope.launch {
            albumViewModel.getAlbums(artistName)
        }

    }
}