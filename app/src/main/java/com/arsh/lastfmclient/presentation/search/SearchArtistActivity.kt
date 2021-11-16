package com.arsh.lastfmclient.presentation.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsh.lastfmclient.R
import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.databinding.ActivityAlbumBinding
import com.arsh.lastfmclient.databinding.ActivitySearchArtistBinding
import com.arsh.lastfmclient.presentation.album.AlbumActivity
import com.arsh.lastfmclient.presentation.album.AlbumAdapter
import com.arsh.lastfmclient.presentation.album.AlbumViewModel
import com.arsh.lastfmclient.presentation.album.AlbumViewModelFactory
import com.arsh.lastfmclient.presentation.di.Injector
import javax.inject.Inject

class SearchArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: SearchArtistViewModelFactory
    private lateinit var searchArtistViewModel: SearchArtistViewModel
    private lateinit var binding: ActivitySearchArtistBinding
    private lateinit var adapter: SearchArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createSearchSubComponent()
            .inject(this)

        searchArtistViewModel =
            ViewModelProvider(this, factory).get(SearchArtistViewModel::class.java)
//        val responseLiveData = searchArtistViewModel.getArtists()
//        responseLiveData.observe(this, Observer {
//            Log.e("TAGGG11", it.toString())
//        })

        initRecyclerView()
        binding.btSearch.setOnClickListener {
            displaySearchedArtists()
        }

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.isNullOrEmpty()){
                    adapter.setList(emptyList())
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = SearchArtistAdapter{
            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)
        }
        binding.searchRecyclerView.adapter = adapter
    }

    private fun displaySearchedArtists() {
        binding.searchProgressBar.visibility = View.VISIBLE
        if (binding.editTextTextPersonName.text.toString().isNotEmpty()) {
            val responseLiveData = searchArtistViewModel.getArtists(
                binding.editTextTextPersonName.text.toString()
            )
            responseLiveData.observe(this, Observer {
                if (it != null) {
                    adapter.setList(it)
                    adapter.notifyDataSetChanged()
                    binding.searchProgressBar.visibility = View.GONE
                } else {
                    binding.searchProgressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "No Data Available", Toast.LENGTH_LONG)
                        .show()
                }
            })
        } else {
            Toast.makeText(this, "Please enter artist name", Toast.LENGTH_SHORT).show()
            binding.searchProgressBar.visibility = View.GONE

        }
    }
}