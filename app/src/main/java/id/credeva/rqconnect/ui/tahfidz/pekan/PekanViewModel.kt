package id.credeva.rqconnect.ui.tahfidz.pekan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.Pekan
import id.credeva.rqconnect.data.repositories.PekanRepository
import id.credeva.rqconnect.util.Coroutines
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