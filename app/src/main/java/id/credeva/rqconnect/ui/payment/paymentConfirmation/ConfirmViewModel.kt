package id.credeva.rqconnect.ui.payment.paymentConfirmation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.repositories.ConfirmPaymentRepository
import id.credeva.rqconnect.util.ApiException
import id.credeva.rqconnect.util.Coroutines
import id.credeva.rqconnect.util.NoInternetException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ConfirmViewModel(
    private val repository: ConfirmPaymentRepository
) : ViewModel() {

    var confirmListener: ConfirmListener? = null

    fun getPayment() = repository

    fun onPaymentClick(imagePath: String) {
        confirmListener?.onStarted()
        val idPayment =
            RequestBody.create("text/plain".toMediaTypeOrNull(), prefManager.spPaymentId.toString())

        Coroutines.main {
            try {
                val detectionResponse =
                    repository.confirmResponse(idPayment, createMultiPart(imagePath))
                detectionResponse.message?.let {
                    confirmListener?.onSucces()
                    return@main
                }
            } catch (e: ApiException) {
                confirmListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                confirmListener?.onFailure(e.message!!)
            }
        }
    }

    private fun createMultiPart(path: String): MultipartBody.Part {
        val uri = Uri.parse(path)
        val file = File(uri.path!!)
        val requestFile = RequestBody.create(
            "*/*".toMediaTypeOrNull(),
            file
        )
        return MultipartBody.Part.createFormData("evidence", file.name, requestFile)
    }
}