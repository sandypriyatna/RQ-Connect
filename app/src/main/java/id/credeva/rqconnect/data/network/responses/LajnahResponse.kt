package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.Lajnah

data class LajnahResponse(
    @SerializedName("data")
    val data: List<Lajnah>?
)