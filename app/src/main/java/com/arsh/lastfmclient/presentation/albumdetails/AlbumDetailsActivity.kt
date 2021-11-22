package com.arsh.lastfmclient.presentation.albumdetails

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.databinding.ActivityAlbumDetailBinding
import com.arsh.lastfmclient.presentation.album.ARTIST_NAME
import com.arsh.lastfmclient.presentation.album.IS_MAIN
import com.arsh.lastfmclient.presentation.di.Injector
import com.bumptech.glide.Glide
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ALBUM_NAME = "album_name"

/**
 * Album Activity represents the details about the album
 */
class AlbumDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumDetailsViewModelFactory
    private lateinit var binding: ActivityAlbumDetailBinding
    private lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    private lateinit var arrayAdapter: ArrayAdapter<String>

    private val trackList : ArrayList<String> by lazy {
        ArrayList()
    }


    private val albumName: String by lazy {
        intent.extras?.get(ALBUM_NAME).toString()
    }

    private val isMain: Boolean by lazy {
        intent.extras?.getBoolean(IS_MAIN,false) ?: false
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

        arrayAdapter = ArrayAdapter(this,
            R.layout.simple_list_item_1, trackList)
        binding.trackList.adapter = arrayAdapter


        binding.tvName.text = albumName
        albumDetailsViewModel = ViewModelProvider(this, factory)[AlbumDetailsViewModel::class.java]


        if (!isMain){
            val responseLiveData =
                artistName?.let { albumDetailsViewModel.getAlbumDetails(albumName, it) }
            responseLiveData?.observe(this, Observer {
                binding.tvArtist.text = it?.artist
                binding.tvName.text = it.name
                binding.tvTracks.visibility = View.VISIBLE
                try {
                    (it?.tracks?.track as ArrayList<LinkedTreeMap<String,Any>>).forEach { trackList.add(
                        it["name"].toString()) }
                    arrayAdapter.addAll(trackList)
                    arrayAdapter.notifyDataSetChanged()
                }catch (e : Exception){
                    e.printStackTrace()
                    binding.tvTracks.visibility = View.GONE
                }
                Glide.with(binding.ivArtist.context)
                    .load(it.image[2].text)
                    .fitCenter()
                    .into(binding.ivArtist)
            })
        }
        else{
            binding.tvTracks.visibility = View.GONE
            albumDetailsViewModel.viewModelScope.launch {
                val localAlbumDetails: List<Album> =
                    albumDetailsViewModel.getLocalAlbumDetails(albumName)

                if (localAlbumDetails.isNotEmpty()) {
                    runOnUiThread {
                        binding.tvArtist.text = localAlbumDetails[0].artistName
                        binding.tvName.text = localAlbumDetails[0].name
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