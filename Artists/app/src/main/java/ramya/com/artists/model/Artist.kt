package ramya.com.artists.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @field:SerializedName("artistName")
    val artistName: String,

    @field:SerializedName("trackName")
    val trackName: String,

    @field:SerializedName("releaseDate")
    val releaseDate: String,

    @field:SerializedName("primaryGenreName")
    val primaryGenreName: String,

    @field:SerializedName("trackPrice")
    val trackPrice: Double
)