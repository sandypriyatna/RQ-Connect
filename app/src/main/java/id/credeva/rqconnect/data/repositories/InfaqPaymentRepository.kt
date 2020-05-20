package id.credeva.rqconnect.data.repositories

import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest
import okhttp3.ResponseBody

class InfaqPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun infaqResponse(amount: Int): ResponseBody {
        return apiRequest { api.storeInfaq(amount) }
    }
}