package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Article
import com.tupaiaer.rqconnect.data.db.entities.Bank

data class ChosePaymentResponse(
    @SerializedName("data")
    val data: List<Bank>?
)