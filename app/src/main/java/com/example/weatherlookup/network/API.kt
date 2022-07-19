package com.example.weatherlookup.network

import com.example.weatherlookup.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("data/2.5/forecast")
    suspend fun getWeatherList(@Query("q") city: String?, @Query("appid") appid: String?): Response<WeatherData>
}