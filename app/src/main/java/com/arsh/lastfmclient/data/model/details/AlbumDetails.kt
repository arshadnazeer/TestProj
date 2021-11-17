package com.arsh.lastfmclient.data.model.details


import com.google.gson.annotations.SerializedName

data class AlbumDetails(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("tags")
    val tags: Any?,
    @SerializedName("tracks")
    val tracks: Tracks,
    @SerializedName("url")
    val url: String,
    @SerializedName("wiki")
    val wiki: Wiki
)