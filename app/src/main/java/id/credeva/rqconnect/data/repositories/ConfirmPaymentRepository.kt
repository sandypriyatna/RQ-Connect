package id.credeva.rqconnect.data.repositories

import id.credeva.rqconnect.data.db.AppDatabase
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest
import id.credeva.rqconnect.data.network.responses.ConfirmationResponse
import id.credeva.rqconnect.data.network.responses.PaymentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

class ConfirmPaymentRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun confirmResponse(id: RequestBody, evidence: MultipartBody.Part): ConfirmationResponse {
        return apiRequest { api.storePayment(id, evidence) }
    }
}
