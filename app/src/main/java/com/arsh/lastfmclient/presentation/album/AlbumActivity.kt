package com.arsh.lastfmclient.presentation.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsh.lastfmclient.databinding.ActivityAlbumBinding
import com.arsh.lastfmclient.presentation.di.Injector
import javax.inject.Inject

class AlbumActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: AlbumViewModelFactory
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var binding: ActivityAlbumBinding
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createAlbumSubComponent()
            .inject(this)

        albumViewModel = ViewModelProvider(this,factory).get(AlbumViewModel::class.java)
        val responseLiveData = albumViewModel.getAlbums()
        responseLiveData.observe(this, Observer {
            Log.e("TAGGG", it.toString())
        })
//        initRecyclerView()

    }

    private fun initRecyclerView(){
        binding.albumRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter()
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