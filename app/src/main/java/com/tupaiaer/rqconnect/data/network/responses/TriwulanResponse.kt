package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Lajnah
import com.tupaiaer.rqconnect.data.db.entities.Pekan
import com.tupaiaer.rqconnect.data.db.entities.Triwulan

data class TriwulanResponse(
    @SerializedName("data")
    val data: List<Triwulan>?
)