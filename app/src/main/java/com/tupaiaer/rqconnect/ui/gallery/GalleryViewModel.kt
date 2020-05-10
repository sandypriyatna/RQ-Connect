package com.tupaiaer.rqconnect.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.Gallery
import com.tupaiaer.rqconnect.data.repositories.GalleryRepository
import com.tupaiaer.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class GalleryViewModel(
    private val repository: GalleryRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _gallery = MutableLiveData<List<Gallery>>()
    val gallery: LiveData<List<Gallery>>
        get() = _gallery

    fun getGallery() {
        job = Coroutines.ioThenMain(
            { repository.getGallery() },
            { _gallery.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}