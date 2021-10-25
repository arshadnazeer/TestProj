package com.arsh.lastfmclient.data.model


import com.arsh.lastfmclient.data.model.Album
import com.arsh.lastfmclient.data.model.Attr
import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: Attr
)