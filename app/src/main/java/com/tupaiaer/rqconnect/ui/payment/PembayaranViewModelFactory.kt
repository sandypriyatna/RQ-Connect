package com.tupaiaer.rqconnect.ui.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tupaiaer.rqconnect.data.repositories.LatePaymentRepository
import com.tupaiaer.rqconnect.data.repositories.PaidOffPaymentRepository
import com.tupaiaer.rqconnect.data.repositories.RemainPaymentRepository

@Suppress("UNCHECKED_CAST")
class PembayaranViewModelFactory(
    private val remainPaymentRepository: RemainPaymentRepository,
    private val paidOffPaymentRepository: PaidOffPaymentRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PembayaranViewModel(remainPaymentRepository, paidOffPaymentRepository) as T
    }
}