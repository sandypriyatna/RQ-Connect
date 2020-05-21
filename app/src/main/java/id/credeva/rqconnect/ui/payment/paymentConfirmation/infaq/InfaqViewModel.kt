package id.credeva.rqconnect.ui.payment.paymentConfirmation.infaq

import android.util.Log
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.repositories.InfaqPaymentRepository
import id.credeva.rqconnect.util.ApiException
import id.credeva.rqconnect.util.Coroutines
import id.credeva.rqconnect.util.NoInternetException

class InfaqViewModel(
    private val repository: InfaqPaymentRepository
) : ViewModel() {

    fun sendInfaq() {
        val amount = prefManager.spInfaq?.toInt()
        Coroutines.main {
            try {
                val detectionResponse =
                    repository.infaqResponse(amount!!.toInt())


                detectionResponse.let {
                    return@main
                }
            } catch (e: ApiException) {
                Log.v("apiError", e.message!!)
            } catch (e: NoInternetException) {
            }
        }
    }
}