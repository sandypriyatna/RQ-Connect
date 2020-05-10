package com.tupaiaer.rqconnect.ui.payment

import android.view.View
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff

interface PaidViewClickListener {
    fun onClickItem(view: View, paidOff: PaidOff)
}