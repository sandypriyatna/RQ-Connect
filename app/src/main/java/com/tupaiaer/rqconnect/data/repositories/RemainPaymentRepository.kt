package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.payment.Remain
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class RemainPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val remain = MutableLiveData<List<Remain>>()

    suspend fun getRemain() = apiRequest { api.getPayment() }
}