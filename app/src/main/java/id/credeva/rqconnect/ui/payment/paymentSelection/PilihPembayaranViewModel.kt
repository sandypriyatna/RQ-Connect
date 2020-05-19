package id.credeva.rqconnect.ui.payment.paymentSelection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.Bank
import id.credeva.rqconnect.data.repositories.ChosePaymentRepository
import id.credeva.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class PilihPembayaranViewModel(
    private val repository: ChosePaymentRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _chosePayment = MutableLiveData<List<Bank>>()
    val chosePayment: LiveData<List<Bank>>
        get() = _chosePayment

    fun getChosePayment() {
        job = Coroutines.ioThenMain(
            { repository.getChosePayment() },
            { _chosePayment.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}