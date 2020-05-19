package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.Pekan

data class PekanResponse(
    @SerializedName("data")
    val data: List<Pekan>?
)