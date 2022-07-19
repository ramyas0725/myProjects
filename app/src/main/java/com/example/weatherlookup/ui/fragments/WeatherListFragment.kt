package com.example.weatherlookup.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherlookup.R
import com.example.weatherlookup.adapter.WeatherListAdapter
import com.example.weatherlookup.databinding.FragmentWeatherListBinding
import com.example.weatherlookup.model.ListItem
import com.example.weatherlookup.repository.WeatherRepository
import com.example.weatherlookup.utils.ItemClickListener
import com.example.weatherlookup.viewmodel.ViewModelProviderFactory
import com.example.weatherlookup.viewmodel.WeatherViewModel

class WeatherListFragment: Fragment(), ItemClickListener {
    private lateinit var weatherListBinding: FragmentWeatherListBinding
    private lateinit var weatherListAdapter: WeatherListAdapter
    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        weatherListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        init()
        return weatherListBinding.root
    }

    private fun init() {
        weatherListBinding.weatherListRecyclerView.setHasFixedSize(true)
        weatherListBinding.weatherListRecyclerView.layoutManager = LinearLayoutManager(activity)
        weatherListAdapter = WeatherListAdapter()
        setupViewModel()



    }

    override fun onResume() {
        super.onResume()
        getActivity()?.setTitle(viewModel.cityName.value);
    }


    private fun setupViewModel() {
        val repository = WeatherRepository()
        val factory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(WeatherViewModel::class.java)
        updateUI()

    }

    private fun updateUI() {
        weatherListAdapter.setData(weatherData = viewModel.weatherData.value?.data)
        weatherListAdapter.setItemClickListerner(this@WeatherListFragment)
        weatherListBinding.weatherListRecyclerView.adapter = weatherListAdapter
    }
    private fun launchWeatherDetailsFragment(){
        val fragmentManager = activity?.supportFragmentManager
        val fragment = WeatherDetailsFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.frameContainer, fragment)?.addToBackStack(null)?.commit()
    }

    override fun onItemClickListener(data: ListItem?) {
        viewModel.listItem.postValue(data)
        launchWeatherDetailsFragment()
    }




}