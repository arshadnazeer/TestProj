package com.arsh.lastfmclient.presentation.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsh.lastfmclient.data.model.search.Artists
import com.arsh.lastfmclient.databinding.ListItemViewBinding
import com.arsh.lastfmclient.presentation.album.AlbumActivity
import com.bumptech.glide.Glide

class SearchArtistAdapter(private val listener : View.OnClickListener) : RecyclerView.Adapter<MyViewHolder>() {
    private val artistList = ArrayList<Artists>()

    fun setList(artists: List<Artists>) {
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MyViewHolder(binding,listener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artistList[position])
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

}

class MyViewHolder(val binding: ListItemViewBinding,val listener: View.OnClickListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artists: Artists) {
        binding.tvName.text = artists.name
        // displaying movie poster
        val posterURL = artists.image[0].text

        // display movie poster using Glide
        Glide.with(binding.ivArtist.context)
            .load(posterURL)
            .into(binding.ivArtist)

        binding.root.setOnClickListener(listener)
    }


}