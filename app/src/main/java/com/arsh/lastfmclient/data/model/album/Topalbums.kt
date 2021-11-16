package com.arsh.lastfmclient.data.model.album


import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: Attr
)