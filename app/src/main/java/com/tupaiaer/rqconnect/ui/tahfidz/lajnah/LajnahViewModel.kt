package com.tupaiaer.rqconnect.ui.tahfidz.lajnah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.Lajnah
import com.tupaiaer.rqconnect.data.repositories.LajnahRepository
import com.tupaiaer.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class LajnahViewModel(
    private val repository: LajnahRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _lajnah = MutableLiveData<List<Lajnah>>()
    val lajnah: LiveData<List<Lajnah>>
        get() = _lajnah

    fun getLajnah() {
        job = Coroutines.ioThenMain(
            { repository.getLajnah() },
            { _lajnah.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}