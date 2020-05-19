package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Bank
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class ChosePaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val bank = MutableLiveData<List<Bank>>()

    suspend fun getChosePayment() = apiRequest { api.getChosePayment() }

}