package com.arsh.lastfmclient.data.model.album


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * The data Class represents the single instance
 */

@Entity(tableName = "top_albums")
data class Album(
    @SerializedName("mbid")
    val mbid: String?,
    @SerializedName("name")
    val name: String?,
    @PrimaryKey
    @SerializedName("playcount")
    val playcount: Int,
    @SerializedName("url")
    val url: String?,
    @SerializedName("image")
    val images: ArrayList<Image>,
    @SerializedName("artist_name")
    var artistName : String

) {
    @Ignore
    @SerializedName("artist")
    val artist: Artist? = null
}

class ImageTypeConverter {
    @TypeConverter
    public fun fromValuesToList(value: ArrayList<Image>?): String? {
        if (value == null) {
            return (null)
        }
        val gson = Gson()
        val type = genericType<ArrayList<Image>>()
        return gson.toJson(value, type)
    }

    @TypeConverter
    public fun toOptionValuesList(value: String?): ArrayList<Image>? {
        if (value == null) {
            return (null)
        }
        val gson = Gson()
        val type = genericType<ArrayList<Image>>()
        return gson.fromJson(value, type)
    }
}


inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type