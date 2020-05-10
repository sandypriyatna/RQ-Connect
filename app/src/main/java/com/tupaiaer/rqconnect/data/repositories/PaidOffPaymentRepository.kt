package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class PaidOffPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val paidOff = MutableLiveData<List<PaidOff>>()

    suspend fun getPaidOff() = apiRequest { api.getPayment() }
}