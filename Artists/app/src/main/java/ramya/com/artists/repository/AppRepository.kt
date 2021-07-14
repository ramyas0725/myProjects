package ramya.com.artists.repository

import ramya.com.artists.network.RetrofitInstance

class AppRepository {
    suspend fun getArtists(name: String) = RetrofitInstance.artistsApi.getArtists(name)
}