package com.arsh.lastfmclient.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsh.lastfmclient.data.model.Album
import com.arsh.lastfmclient.databinding.ListItemBinding

class AlbumAdapter : RecyclerView.Adapter<MyViewHolder>(){
    private val albumeList = ArrayList<Album>()

    fun setList(albums: List<Album>){
        albumeList.clear()
        albumeList.addAll(albums)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(
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

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(album:Album){
        binding.tvName.text = album.name
        binding.tvMbid.text = album.mbid
        binding.tvPlaycount.text = album.playcount.toString()
    }

}