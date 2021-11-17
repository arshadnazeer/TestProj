package com.arsh.lastfmclient.data.model.album


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Image(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val text: String
)