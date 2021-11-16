package com.arsh.lastfmclient.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.databinding.ListItemViewBinding
import com.bumptech.glide.Glide

class AlbumAdapter : RecyclerView.Adapter<MyViewHolder>(){
    private val albumeList = ArrayList<Album>()

    fun setList(albums: List<Album>){
        albumeList.clear()
        albumeList.addAll(albums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albumeList[position])
    }

    override fun getItemCount(): Int {
        return albumeList.size
    }

}

class MyViewHolder(val binding: ListItemViewBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(album: Album){
        binding.tvName.text = album.name
        // displaying movie poster
        val posterURL = album.images?.get(0)?.text

        // display movie poster using Glide
        Glide.with(binding.ivArtist.context)
            .load(posterURL)
            .into(binding.ivArtist)
    }

}