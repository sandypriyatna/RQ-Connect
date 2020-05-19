package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.payment.Remain
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class RemainPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val remain = MutableLiveData<List<Remain>>()

    suspend fun getRemain() = apiRequest { api.getPayment() }
}