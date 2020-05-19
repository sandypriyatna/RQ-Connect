package id.credeva.rqconnect.ui.payment.paymentSelection

import android.view.View
import id.credeva.rqconnect.data.db.entities.Bank

interface PilihPembayaranViewClickListener {
    fun onBankViewClickListener(view: View, bank: Bank)
}