package com.tupaiaer.rqconnect.ui.payment.paymentConfirmation

interface ConfirmListener {
    fun onStarted()
    fun onSucces(message: String)
    fun onFailure(message: String)
}