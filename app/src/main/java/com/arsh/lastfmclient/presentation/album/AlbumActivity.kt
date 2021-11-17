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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val ARTIST_NAME = "artist_name"

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
//        val responseLiveData = albumViewModel.getAlbums()
//        responseLiveData.observe(this, Observer {
//            Log.e("TAGGG", it.toString())
//        })
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.albumRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = AlbumAdapter(object : AlbumItemContract {
            override fun favPos(pos: Int) {
                albumViewModel.viewModelScope.launch {
                    adapter.getList()[pos].name?.let {
                        if(albumViewModel.fetchFavoriteState(it))
                            albumViewModel.removeFromFavorites(adapter.getList()[pos])
                        else
                            albumViewModel.addToFavorites(adapter.getList()[pos])
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
        val responseLiveData = albumViewModel.getAlbums(artistName)
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.albumProgressBar.visibility = View.GONE
            } else {
                binding.albumProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No Data Available", Toast.LENGTH_LONG).show()
            }
        })
    }
}