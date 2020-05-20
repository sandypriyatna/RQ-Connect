package id.credeva.rqconnect.ui.payment.paymentConfirmation.deposit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.ConfirmPaymentRepository
import id.credeva.rqconnect.data.repositories.DepositPaymentRepository

@Suppress("UNCHECKED_CAST")
class DepositViewModelFactory(
    private val repository: DepositPaymentRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DepositViewModel(
            repository
        ) as T
    }
}