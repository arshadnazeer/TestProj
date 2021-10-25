package com.arsh.lastfmclient.data.model


import com.google.gson.annotations.SerializedName

data class ArtistTopAlbumList(
    @SerializedName("topalbums")
    val topalbums: Topalbums
)