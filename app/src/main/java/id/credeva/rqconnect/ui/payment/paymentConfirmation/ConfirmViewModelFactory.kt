package id.credeva.rqconnect.ui.payment.paymentConfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.ConfirmPaymentRepository

@Suppress("UNCHECKED_CAST")
class ConfirmViewModelFactory(
    private val repository: ConfirmPaymentRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfirmViewModel(
            repository
        ) as T
    }
}