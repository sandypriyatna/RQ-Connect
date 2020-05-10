package com.tupaiaer.rqconnect.ui.tahfidz.triwulan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.Triwulan
import com.tupaiaer.rqconnect.data.repositories.TriwulanRepository
import com.tupaiaer.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class TriwulanViewModel(
    private val repository: TriwulanRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _triwulan = MutableLiveData<List<Triwulan>>()
    val triwulan: LiveData<List<Triwulan>>
        get() = _triwulan

    fun getTriwulan() {
        job = Coroutines.ioThenMain(
            { repository.getTriwulan() },
            { _triwulan.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}