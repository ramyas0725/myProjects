package ramya.com.artists.model

import com.google.gson.annotations.SerializedName

data class Artists(
    @field:SerializedName("resultCount")
    val resultCount: Int? = null,

    @field:SerializedName("results")
    val artists: MutableList<Artist>? = null
)