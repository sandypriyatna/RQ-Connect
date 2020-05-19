package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.Triwulan

data class TriwulanResponse(
    @SerializedName("data")
    val data: List<Triwulan>?
)