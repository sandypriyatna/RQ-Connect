package id.credeva.rqconnect.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.GalleryRepository

@Suppress("UNCHECKED_CAST")
class GalleryViewModelFactory(
    private val repository: GalleryRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(repository) as T
    }
}