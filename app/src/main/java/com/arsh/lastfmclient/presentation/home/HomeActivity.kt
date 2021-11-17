package com.arsh.lastfmclient.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arsh.lastfmclient.databinding.ActivityHomeBinding
import com.arsh.lastfmclient.presentation.album.AlbumAdapter
import com.arsh.lastfmclient.presentation.album.AlbumItemContract
import com.arsh.lastfmclient.presentation.albumdetails.ALBUM_NAME
import com.arsh.lastfmclient.presentation.albumdetails.AlbumDetailActivity
import com.arsh.lastfmclient.presentation.di.Injector
import com.arsh.lastfmclient.presentation.search.SearchArtistActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HomeViewModelFactory

    private lateinit var binding: ActivityHomeBinding

    private lateinit var homeViewModel: HomeViewModel

    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter(object : AlbumItemContract {
            override fun favPos(pos: Int) {
                homeViewModel.viewModelScope.launch {
                    albumAdapter.getList()[pos].name?.let {
                        homeViewModel.removeFromFavorites(albumAdapter.getList()[pos])
                    }

                    albumAdapter.getList().remove(albumAdapter.getList()[pos])

                    runOnUiThread {
                        albumAdapter.notifyItemRemoved(pos)
                    }
                }
            }

            override fun clickedPos(pos: Int) {
                val intent = Intent(
                    this@HomeActivity, AlbumDetailActivity::class.java
                )
                intent.putExtra(ALBUM_NAME, albumAdapter.getList()[pos].name)
                startActivity(intent)
            }

            override fun localFavoriteState(albumName: String): Boolean {
                var fav: Boolean
                runBlocking {
                    val favAsync = async {
                        homeViewModel.fetchFavoriteState(albumName)
                    }
                    fav = favAsync.await()
                }

                return fav
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createHomeSubComponent()
            .inject(this)

        homeViewModel =
            ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.button.setOnClickListener {
            val intent = Intent(this, SearchArtistActivity::class.java)
            startActivity(intent)
        }

        binding.rvAlbums.adapter = albumAdapter

    }

    override fun onResume() {
        super.onResume()
        fetchAlbums()
    }


    private fun fetchAlbums() {
        homeViewModel.getAlbums().observe(this, {
            if (it.isNullOrEmpty())
                binding.tvNoItems.visibility = View.VISIBLE
            else {
                binding.tvNoItems.visibility = View.GONE
                albumAdapter.setList(it)
                albumAdapter.notifyDataSetChanged()
            }
        })
    }
}