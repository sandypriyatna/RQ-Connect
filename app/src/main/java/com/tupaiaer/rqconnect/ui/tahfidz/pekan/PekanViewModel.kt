package com.tupaiaer.rqconnect.ui.tahfidz.pekan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.Pekan
import com.tupaiaer.rqconnect.data.repositories.PekanRepository
import com.tupaiaer.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class PekanViewModel(
    private val repository: PekanRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _pekan = MutableLiveData<List<Pekan>>()
    val pekan: LiveData<List<Pekan>>
        get() = _pekan

    fun getPekan() {
        job = Coroutines.ioThenMain(
            { repository.getPekan() },
            { _pekan.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}