package com.arsh.lastfmclient.presentation.albumdetails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.databinding.ActivityAlbumDetailBinding
import com.arsh.lastfmclient.presentation.album.ARTIST_NAME
import com.arsh.lastfmclient.presentation.di.Injector
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ALBUM_NAME = "album_name"

class AlbumDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumDetailsViewModelFactory
    private lateinit var binding: ActivityAlbumDetailBinding
    private lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    private val albumName: String by lazy {
        intent.extras?.get(ALBUM_NAME).toString()
    }

    private val artistName: String? by lazy {
        intent.extras?.get(ARTIST_NAME) as? String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createAlbumDetailsSubComponent()
            .inject(this)


        binding.tvName.text = albumName

        albumDetailsViewModel = ViewModelProvider(this, factory)[AlbumDetailsViewModel::class.java]

        artistName?.let{
            val responseLiveData = albumDetailsViewModel.getAlbumDetails(albumName, it)
            responseLiveData.observe(this, Observer {
                Log.e("TAGGG", it?.artist.toString())
                binding.tvArtist.text = it?.artist
                Glide.with(binding.ivArtist.context)
                    .load(it?.image?.get(2)?.text)
                    .fitCenter()
                    .into(binding.ivArtist)
            })
        }

        if(artistName == null){
            albumDetailsViewModel.viewModelScope.launch {
                val localAlbumDetails: List<Album> = albumDetailsViewModel.getLocalAlbumDetails(albumName)

                if(localAlbumDetails.isNotEmpty()) {
                    runOnUiThread {
                        binding.tvArtist.text = localAlbumDetails[0].artist.toString()
                        Glide.with(binding.ivArtist.context)
                            .load(localAlbumDetails[0].images[2].text)
                            .fitCenter()
                            .into(binding.ivArtist)
                    }
                }
            }
        }

    }
}