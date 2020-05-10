package com.tupaiaer.rqconnect.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff
import com.tupaiaer.rqconnect.data.db.entities.payment.Remain
import com.tupaiaer.rqconnect.data.repositories.PaidOffPaymentRepository
import com.tupaiaer.rqconnect.data.repositories.RemainPaymentRepository
import com.tupaiaer.rqconnect.util.Coroutines
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