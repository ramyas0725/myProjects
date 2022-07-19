package com.example.weatherlookup.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlookup.R
import com.example.weatherlookup.databinding.FragmentHomeBinding
import com.example.weatherlookup.repository.WeatherRepository
import com.example.weatherlookup.ui.HomeActivity
import com.example.weatherlookup.viewmodel.ViewModelProviderFactory
import com.example.weatherlookup.viewmodel.WeatherViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.weatherlookup.utils.Resource
import com.example.weatherlookup.utils.errorSnack

class HomeFragment: Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupViewModel()
        observerWeatherData()
        homeBinding.lookupButton.setOnClickListener{
            if (!homeBinding.cityNameEditText.text.isNullOrEmpty()){
                //Log.d("ramya", "onCreateView: button onclick")
                viewModel.getWeatherList(homeBinding.cityNameEditText.text.toString())
                viewModel.cityName.value = homeBinding.cityNameEditText.text.toString()
            }else {
                homeBinding.cityNameEditText.error= "Please enter city name"
            }
        }
        return homeBinding.root
    }

    private fun setupViewModel() {
        val repository = WeatherRepository()
        val factory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(WeatherViewModel::class.java)


    }
    private fun launchWeatherListFragment(){
        val fragmentManager = activity?.supportFragmentManager
        val fragment = WeatherListFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.frameContainer, fragment)?.addToBackStack(null)?.commit()
    }
    private fun observerWeatherData() {
        viewModel.weatherData.observe(viewLifecycleOwner, Observer { response ->
           // Log.d("ramya", "weatherData.observe: ")
            when (response) {
                is Resource.Success -> {
                   // Log.d("ramya", "getWeatherList success: ")
                    (activity as HomeActivity).hideProgressBar()
                    launchWeatherListFragment()
                }

                is Resource.Error -> {
                    //Log.d("ramya", "weatherData Error: ")
                    (activity as HomeActivity).hideProgressBar()
                    response.message?.let { message ->
                        homeBinding.rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    (activity as HomeActivity).showProgressBar()
                }
            }
        })
    }



    override fun onDestroy() {
        super.onDestroy()
             viewModel.weatherData.removeObservers(this)
    }

}