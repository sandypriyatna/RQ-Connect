package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.payment.Late
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class LatePaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val late = MutableLiveData<List<Late>>()

    suspend fun getLate() = apiRequest { api.getPayment() }
}