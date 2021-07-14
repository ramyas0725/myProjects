package ramya.com.artists.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ramya.com.artists.repository.AppRepository
import ramya.com.artists.util.Resource
import kotlinx.coroutines.launch
import ramya.com.artists.model.Artists
import retrofit2.Response

class ArtistsViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    val artistsData: MutableLiveData<Resource<Artists>> = MutableLiveData()


    fun getArtists(name: String) = viewModelScope.launch {
        fetchArtists(name)
    }


    private suspend fun fetchArtists(name: String) {
        artistsData.postValue(Resource.Loading())
        val response = appRepository.getArtists(name)
        artistsData.postValue(handlePicsResponse(response))

    }

    private fun handlePicsResponse(response: Response<Artists>): Resource<Artists> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}