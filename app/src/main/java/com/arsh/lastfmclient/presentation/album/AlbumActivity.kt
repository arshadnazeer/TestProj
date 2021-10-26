package com.arsh.lastfmclient.presentation.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.databinding.ActivityAlbumBinding
import com.arsh.lastfmclient.presentation.di.Injector
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumViewModelFactory
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createAlbumSubComponent()
            .inject(this)

        albumViewModel = ViewModelProvider(this,factory).get(AlbumViewModel::class.java)
        val responseLiveData = albumViewModel.getAlbums()
        responseLiveData.observe(this, Observer {
            Log.e("TAGG",it.toString())
        })

    }
}