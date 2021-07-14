package ramya.com.artists.network

import ramya.com.artists.model.Artists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("search")
    suspend fun getArtists(@Query("term") term: String?): Response<Artists>
}