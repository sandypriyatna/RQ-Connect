package com.tupaiaer.rqconnect.ui.payment.paymentSelection

import android.view.View
import com.tupaiaer.rqconnect.data.db.entities.Article
import com.tupaiaer.rqconnect.data.db.entities.Bank

interface PilihPembayaranViewClickListener {
    fun onBankViewClickListener(view: View, bank: Bank)
}