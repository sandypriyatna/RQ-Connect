package id.credeva.rqconnect.ui.gallery.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.GalleryDetail
import id.credeva.rqconnect.data.repositories.GalleryDetailRepository
import id.credeva.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class GalleryDetailViewModel(
    private val repository: GalleryDetailRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _gallerydetail = MutableLiveData<List<GalleryDetail>>()
    val galleryDetail: LiveData<List<GalleryDetail>>
        get() = _gallerydetail

    fun getGalleryDetail() {
        job = Coroutines.ioThenMain(
            { repository.getGalleryDetail() },
            { _gallerydetail.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}