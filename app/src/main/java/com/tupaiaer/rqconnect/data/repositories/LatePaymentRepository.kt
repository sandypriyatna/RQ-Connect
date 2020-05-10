package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.payment.Late
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class LatePaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val late = MutableLiveData<List<Late>>()

    suspend fun getLate() = apiRequest { api.getPayment() }
}