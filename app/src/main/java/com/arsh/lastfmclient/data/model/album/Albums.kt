package com.arsh.lastfmclient.data.model.album


import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("topalbums")
    val topalbums: Topalbums
)