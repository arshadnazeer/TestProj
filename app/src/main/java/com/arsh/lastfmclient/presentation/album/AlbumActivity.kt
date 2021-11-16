package com.arsh.lastfmclient.presentation.album

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsh.lastfmclient.databinding.ActivityAlbumBinding
import com.arsh.lastfmclient.presentation.albumdetails.AlbumDetailActivity
import com.arsh.lastfmclient.presentation.di.Injector
import com.arsh.lastfmclient.presentation.search.SearchArtistAdapter
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumViewModelFactory
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var binding: ActivityAlbumBinding
    private lateinit var adapter: SearchArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createAlbumSubComponent()
            .inject(this)

        albumViewModel = ViewModelProvider(this,factory).get(AlbumViewModel::class.java)
//        val responseLiveData = albumViewModel.getAlbums()
//        responseLiveData.observe(this, Observer {
//            Log.e("TAGGG", it.toString())
//        })
        initRecyclerView()

    }

    private fun initRecyclerView(){
        binding.searchRecyclerView.layoutManager = GridLayoutManager(this,2)
        adapter = SearchArtistAdapter{
            val intent = Intent(this, AlbumDetailActivity::class.java)
            startActivity(intent)
        }
        binding.albumRecyclerView.adapter = adapter
        displayPopularAlbums()
    }

    private fun displayPopularAlbums(){
        binding.albumProgressBar.visibility = View.VISIBLE
        val responseLiveData = albumViewModel.getAlbums()
        responseLiveData.observe(this, Observer {
            if (it!=null){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.albumProgressBar.visibility = View.GONE
            }else{
                binding.albumProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No Data Available", Toast.LENGTH_LONG).show()
            }
        })
    }
}