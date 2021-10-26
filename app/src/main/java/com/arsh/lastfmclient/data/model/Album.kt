package com.arsh.lastfmclient.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "top_albums")
data class  Album(

    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("name")
    val name: String?,
    @PrimaryKey
    @SerializedName("playcount")
    val playcount: Int?,
    @SerializedName("url")
    val url: String?
)