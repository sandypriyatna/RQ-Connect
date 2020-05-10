package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.User

data class AuthResponse(
    @SerializedName("data")
    val data: User?
)