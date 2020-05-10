package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Lajnah

data class LajnahResponse(
    @SerializedName("data")
    val data: List<Lajnah>?
)