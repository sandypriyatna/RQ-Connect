package com.tupaiaer.rqconnect.ui.payment.paymentConfirmation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.data.repositories.ConfirmPaymentRepository
import com.tupaiaer.rqconnect.util.ApiException
import com.tupaiaer.rqconnect.util.Coroutines
import com.tupaiaer.rqconnect.util.NoInternetException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ConfirmViewModel(
    private val repository: ConfirmPaymentRepository
) : ViewModel() {

    var confirmListener: ConfirmListener? = null

    fun sendData(imagePath: String) {
        confirmListener?.onStarted()
        Log.v("Image Path: ", imagePath)

        val idPayment =
            RequestBody.create("text/plain".toMediaTypeOrNull(), prefManager.spIdPayment.toString())

        Coroutines.main {
            try {
                val detectionResponse =
                    repository.confirmResponse(idPayment, createMultiPart(imagePath))

                detectionResponse.let {
                    return@main
                }
            } catch (e: ApiException) {
                Log.v("apiError", e.message!!)
                confirmListener?.onFailure(e.message)
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