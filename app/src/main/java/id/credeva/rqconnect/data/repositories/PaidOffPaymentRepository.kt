package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.payment.PaidOff
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class PaidOffPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val paidOff = MutableLiveData<List<PaidOff>>()

    suspend fun getPaidOff() = apiRequest { api.getPayment() }
}