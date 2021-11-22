package com.arsh.lastfmclient.data.model.details


import com.google.gson.annotations.SerializedName

/**
 * The data Class represents the single instance
 */
data class Details(
    @SerializedName("album")
    val albumDetails: AlbumDetails
)