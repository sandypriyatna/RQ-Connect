package id.credeva.rqconnect.ui.payment.paymentConfirmation.deposit

import android.util.Log
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.repositories.DepositPaymentRepository
import id.credeva.rqconnect.util.ApiException
import id.credeva.rqconnect.util.Coroutines
import id.credeva.rqconnect.util.NoInternetException

class DepositViewModel(
    private val repository: DepositPaymentRepository
) : ViewModel() {

    fun sendDeposit() {
        val amount = prefManager.spDeposit?.toInt()
        Coroutines.main {
            try {
                val detectionResponse =
                    repository.depositResponse(amount!!.toInt())

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