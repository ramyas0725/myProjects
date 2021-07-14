package ramya.com.artists.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ramya.com.artists.R
import ramya.com.artists.adapter.ArtistsAdapter
import ramya.com.artists.repository.AppRepository
import ramya.com.artists.util.Resource
import ramya.com.artists.util.errorSnack
import ramya.com.artists.viewmodel.ArtistsViewModel
import ramya.com.artists.viewmodel.ViewModelProviderFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ArtistsViewModel
    lateinit var artistsAdapter: ArtistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        rvArtists.setHasFixedSize(true)
        rvArtists.layoutManager = LinearLayoutManager(this)
        artistsAdapter = ArtistsAdapter()
        setupViewModel()

        btnSearch.setOnClickListener {
            if (etName.text.toString().length>0) {
                viewModel.getArtists(etName.text.toString())
            }
        }
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(ArtistsViewModel::class.java)
        getArtists()
    }

    private fun getArtists() {
        viewModel.artistsData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d("ramya", "getArtists success: ")
                    hideProgressBar()
                    response.data?.let { artists ->
                        artistsAdapter.setData(artists = artists)
                        rvArtists.adapter = artistsAdapter
                    }
                }

                is Resource.Error -> {
                    Log.d("ramya", "getArtists Error: ")
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

}