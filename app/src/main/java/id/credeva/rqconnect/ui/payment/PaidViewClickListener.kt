package id.credeva.rqconnect.ui.payment

import android.view.View
import id.credeva.rqconnect.data.db.entities.payment.PaidOff

interface PaidViewClickListener {
    fun onClickItem(view: View, paidOff: PaidOff)
}