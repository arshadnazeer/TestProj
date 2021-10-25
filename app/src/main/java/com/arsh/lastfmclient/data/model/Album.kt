package com.arsh.lastfmclient.data.model
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "top_albums")
data class  Album(

    @SerializedName("artist")
    val artist: Artist?,
    @SerializedName("image")
    val image: List<Image>?,
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playcount")
    val playcount: Int?,
    @SerializedName("url")
    val url: String?
)