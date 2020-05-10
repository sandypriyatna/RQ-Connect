package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Pekan

data class PekanResponse(
    @SerializedName("data")
    val data: List<Pekan>?
)