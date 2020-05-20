package id.credeva.rqconnect.data.repositories

import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest
import okhttp3.ResponseBody

class DepositPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun depositResponse(amount: Int): ResponseBody {
        return apiRequest { api.storeDeposit(amount) }
    }
}