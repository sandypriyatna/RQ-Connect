package id.credeva.rqconnect.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.payment.PaidOff
import id.credeva.rqconnect.data.db.entities.payment.Remain
import id.credeva.rqconnect.data.repositories.PaidOffPaymentRepository
import id.credeva.rqconnect.data.repositories.RemainPaymentRepository
import id.credeva.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class PembayaranViewModel(
    private val remainRepository: RemainPaymentRepository,
    private val paidRepository: PaidOffPaymentRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _remain = MutableLiveData<List<Remain>>()
    val remain: LiveData<List<Remain>>
        get() = _remain

    private val _paid = MutableLiveData<List<PaidOff>>()
    val paid: LiveData<List<PaidOff>>
        get() = _paid

    fun getRemain() {
        job = Coroutines.ioThenMain(
            { remainRepository.getRemain() },
            { _remain.value = it!!.data!!.remain }
        )
    }

    fun getPaidOff() {
        job = Coroutines.ioThenMain(
            { paidRepository.getPaidOff() },
            { _paid.value = it!!.data!!.paidOff }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}