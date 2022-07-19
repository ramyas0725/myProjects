package com.example.weatherlookup.repository

import com.example.weatherlookup.network.RetrofitInstance

class WeatherRepository {
    suspend fun getWeatherList(cityName: String, api_key: String) = RetrofitInstance.weatherApi.getWeatherList(cityName, api_key)
}