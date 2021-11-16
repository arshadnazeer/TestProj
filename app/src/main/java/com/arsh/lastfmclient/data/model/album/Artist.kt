package com.arsh.lastfmclient.data.model.album


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)