package com.arsh.lastfmclient.presentation.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.arsh.lastfmclient.databinding.ActivitySearchArtistBinding
import com.arsh.lastfmclient.presentation.album.*
import com.arsh.lastfmclient.presentation.di.Injector
import javax.inject.Inject

/**
 * Displays the list of artist searched
 */
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

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()) {
                    adapter.setList(emptyList())
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = SearchArtistAdapter(object : SearchItemContract {
            override fun clickedPos(pos: Int) {
                val intent = Intent(
                    this@SearchArtistActivity, AlbumActivity::class.java
                )
                intent.putExtra(ARTIST_NAME, adapter.getList()[pos].name)
                startActivity(intent)
            }
        })
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