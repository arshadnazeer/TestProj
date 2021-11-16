package com.arsh.lastfmclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arsh.lastfmclient.databinding.ActivityHomeBinding
import com.arsh.lastfmclient.presentation.album.AlbumActivity
import com.arsh.lastfmclient.presentation.search.SearchArtistActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, SearchArtistActivity::class.java)
            startActivity(intent)
        }

    }
}