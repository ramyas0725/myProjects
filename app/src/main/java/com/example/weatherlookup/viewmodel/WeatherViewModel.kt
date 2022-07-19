package com.example.weatherlookup.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherlookup.model.ListItem
import com.example.weatherlookup.model.WeatherData
import com.example.weatherlookup.repository.WeatherRepository
import com.example.weatherlookup.utils.Resource
import kotlinx.coroutines.launch
import com.example.weatherlookup.utils.Constants.API_KEY
import retrofit2.Response

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val weatherData: MutableLiveData<Resource<WeatherData>> = MutableLiveData()
    val listItem: MutableLiveData<ListItem> = MutableLiveData()
    val cityName: MutableLiveData<String> = MutableLiveData()


    fun getWeatherList(name: String) = viewModelScope.launch {
        fetchWeatherList(name)
    }


    private suspend fun fetchWeatherList(name: String) {
        Log.d("ramya", "fetchWeatherList: ")
        weatherData.postValue(Resource.Loading())
        val response = weatherRepository.getWeatherList(name, API_KEY)
        weatherData.postValue(handlePicsResponse(response))

    }

    private fun handlePicsResponse(response: Response<WeatherData>): Resource<WeatherData> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}