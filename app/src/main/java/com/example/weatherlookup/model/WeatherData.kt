package com.example.weatherlookup.model

import com.google.gson.annotations.SerializedName

data class WeatherData(@SerializedName("city")
                   val city: City,
                       @SerializedName("cnt")
                   val cnt: Int = 0,
                       @SerializedName("cod")
                   val cod: String = "",
                       @SerializedName("message")
                   val message: Int = 0,
                       @SerializedName("list")
                   val list: List<ListItem>?)