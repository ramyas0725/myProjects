package com.example.weatherlookup.utils

import com.example.weatherlookup.model.ListItem
import com.example.weatherlookup.model.WeatherData

interface ItemClickListener {
    fun onItemClickListener(data: ListItem?)
}