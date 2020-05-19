package id.credeva.rqconnect.ui.tahfidz.triwulan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.Triwulan
import id.credeva.rqconnect.data.repositories.TriwulanRepository
import id.credeva.rqconnect.util.Coroutines
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