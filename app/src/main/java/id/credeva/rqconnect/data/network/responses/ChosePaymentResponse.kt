package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.Bank

data class ChosePaymentResponse(
    @SerializedName("data")
    val data: List<Bank>?
)