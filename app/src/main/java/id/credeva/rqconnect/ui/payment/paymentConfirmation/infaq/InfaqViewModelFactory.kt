package id.credeva.rqconnect.ui.payment.paymentConfirmation.infaq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.ConfirmPaymentRepository
import id.credeva.rqconnect.data.repositories.InfaqPaymentRepository

@Suppress("UNCHECKED_CAST")
class InfaqViewModelFactory(
    private val repository: InfaqPaymentRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InfaqViewModel(
            repository
        ) as T
    }
}