package id.credeva.rqconnect.ui.gallery.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.*

@Suppress("UNCHECKED_CAST")
class GalleryDetailViewModelFactory(
    private val repository: GalleryDetailRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryDetailViewModel(repository) as T
    }
}