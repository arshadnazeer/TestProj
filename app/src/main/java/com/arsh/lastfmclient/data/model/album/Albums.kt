package com.arsh.lastfmclient.data.model.album


import com.google.gson.annotations.SerializedName

/**
 * The data Class represents the single instance
 */

data class Albums(
    @SerializedName("topalbums")
    val topalbums: Topalbums
)