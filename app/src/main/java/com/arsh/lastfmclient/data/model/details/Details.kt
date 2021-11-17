package com.arsh.lastfmclient.data.model.details


import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("album")
    val albumDetails: AlbumDetails
)