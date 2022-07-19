package com.example.weatherlookup.model

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("main")
                    val main: Main)