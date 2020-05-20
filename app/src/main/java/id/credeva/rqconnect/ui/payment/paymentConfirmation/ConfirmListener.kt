package id.credeva.rqconnect.ui.payment.paymentConfirmation

interface ConfirmListener {
    fun onStarted()
    fun onSucces()
    fun onFailure(message: String)
}