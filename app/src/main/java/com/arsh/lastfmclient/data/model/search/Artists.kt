package com.arsh.lastfmclient.data.model.search


import com.google.gson.annotations.SerializedName

/**
 * The data Class represents the single instance
 */
data class Artists(
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("url")
    val url: String
)