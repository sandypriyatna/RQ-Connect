package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Bank
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class ChosePaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val bank = MutableLiveData<List<Bank>>()

    suspend fun getChosePayment() = apiRequest { api.getChosePayment() }

}