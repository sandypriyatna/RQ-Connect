package com.tupaiaer.rqconnect.ui.gallery.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.GalleryDetail
import com.tupaiaer.rqconnect.data.repositories.GalleryDetailRepository
import com.tupaiaer.rqconnect.util.Coroutines
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