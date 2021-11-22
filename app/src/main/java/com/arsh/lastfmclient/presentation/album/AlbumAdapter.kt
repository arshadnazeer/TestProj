package com.arsh.lastfmclient.presentation.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsh.lastfmclient.R
import com.arsh.lastfmclient.data.model.album.Album
import com.arsh.lastfmclient.databinding.ListItemViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * The adapter class represents the list of data that is displayed in the recycler view
 */
class AlbumAdapter(private val albumItemContract: AlbumItemContract) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val albumeList = ArrayList<Album>()

    fun setList(albums: List<Album>) {
        albumeList.clear()
        albumeList.addAll(albums)
    }

    fun getList(): ArrayList<Album> {
        return albumeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MyViewHolder(binding, albumItemContract)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albumeList[position])
    }

    override fun getItemCount(): Int {
        return albumeList.size
    }

}

class MyViewHolder(
    private val binding: ListItemViewBinding,
    private val albumItemContract: AlbumItemContract
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(album: Album) {
        binding.tvName.text = album.name
        val posterURL = album.images[2].text

        Glide.with(binding.ivArtist.context)
            .load(posterURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivArtist)

        binding.root.setOnClickListener {
            albumItemContract.clickedPos(adapterPosition)
        }

        binding.ivFavorite.visibility = View.VISIBLE

        binding.ivFavorite.setOnClickListener {
            albumItemContract.favPos(adapterPosition)
        }

        album.name?.let {
            binding.ivFavorite.setImageResource(
                if (albumItemContract.localFavoriteState(it))
                    R.drawable.ic_baseline_favorite
                else
                    R.drawable.ic_baseline_favorite_border
            )
        }
    }

}
