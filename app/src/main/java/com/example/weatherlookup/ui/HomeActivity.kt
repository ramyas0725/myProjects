package com.example.weatherlookup.ui


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherlookup.R
import com.example.weatherlookup.databinding.ActivityHomeBinding
import com.example.weatherlookup.ui.fragments.HomeFragment
import com.example.weatherlookup.viewmodel.WeatherViewModel


class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        showUpButton()
        launchHomeFragment()

    }

    private fun launchHomeFragment(){
       val fragmentManager = supportFragmentManager
        val fragment = HomeFragment()
        fragmentManager.beginTransaction().replace(R.id.frameContainer, fragment).addToBackStack(null).commit()
    }

    fun showUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun hideUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

     fun hideProgressBar() {
        homeBinding.progress.visibility = View.GONE
    }

     fun showProgressBar() {
        homeBinding.progress.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val count: Int = supportFragmentManager.getBackStackEntryCount()
        if (count == 0) {
             super.onBackPressed()
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}