package com.example.weatherlookup.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlookup.R
import com.example.weatherlookup.databinding.FragmentWeatherDetailsBinding
import com.example.weatherlookup.repository.WeatherRepository
import com.example.weatherlookup.viewmodel.ViewModelProviderFactory
import com.example.weatherlookup.viewmodel.WeatherViewModel

class WeatherDetailsFragment: Fragment() {
    private lateinit var weatherDetailsBinding: FragmentWeatherDetailsBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        weatherDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_details, container, false)
        setupViewModel()
        return weatherDetailsBinding.root
    }

    private fun setupViewModel() {
        val repository = WeatherRepository()
        val factory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(WeatherViewModel::class.java)
       // Log.d("ramya", " viewModel data "+viewModel.listItem.value)
        updateUI()

    }
    override fun onResume() {
        super.onResume()
        getActivity()?.setTitle(viewModel.cityName.value);
    }

    private fun updateUI() {
        weatherDetailsBinding.weatherName.text = viewModel.listItem.value?.weather?.get(0)?.main
        weatherDetailsBinding.weatherDescription.text = viewModel.listItem.value?.weather?.get(0)?.description
        weatherDetailsBinding.temperatureTV.text = viewModel.listItem.value?.main?.temp.toString()
        weatherDetailsBinding.feelsLikeTV.text = "Feels Like " +viewModel.listItem.value?.main?.feelsLike.toString()
    }

}