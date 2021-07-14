package ramya.com.artists.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ramya.com.artists.repository.AppRepository

class ViewModelProviderFactory(
    val app: Application,
    val appRepository: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistsViewModel::class.java)) {
            return ArtistsViewModel(app, appRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}