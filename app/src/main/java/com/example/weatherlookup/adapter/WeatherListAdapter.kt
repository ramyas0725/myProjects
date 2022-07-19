package com.example.weatherlookup.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherlookup.R
import com.example.weatherlookup.model.ListItem
import com.example.weatherlookup.model.WeatherData
import com.example.weatherlookup.utils.ItemClickListener


class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         lateinit var weatherName : TextView
         lateinit var weatherTmp : TextView
         init {
             weatherName = itemView.findViewById(R.id.weatherName)
             weatherTmp = itemView.findViewById(R.id.weatherTmp)
         }

         fun bind(listItem: ListItem){
             weatherName.text = listItem.weather?.get(0)?.main
             weatherTmp.text=listItem.main.temp.toString()
         }
     }

    var weatherListData: List<ListItem>? = emptyList()

    fun setData(weatherData: WeatherData?) {
        weatherListData = weatherData?.list
    }

    fun setItemClickListerner(listener: ItemClickListener) {
        itemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layouManager = LayoutInflater.from(parent.context)
        val v = layouManager.inflate(R.layout.item_weather,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherData = weatherListData?.get(position)

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClickListener(weatherData)
        }
       weatherData?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int {
        return weatherListData?.size!!
    }



}