package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.User

data class AuthResponse(
    @SerializedName("data")
    val data: User?
)