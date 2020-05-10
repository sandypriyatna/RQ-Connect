package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.payment.Late
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff
import com.tupaiaer.rqconnect.data.db.entities.payment.Remain

data class PaymentResponse(
    @SerializedName("data")
    val data: PaymentData?
) {

    data class PaymentData(
        @SerializedName("late")
        val late: List<Late>?,
        @SerializedName("paid_off")
        val paidOff: List<PaidOff>?,
        @SerializedName("remain")
        val remain: List<Remain>?
    )
}