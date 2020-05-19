package id.credeva.rqconnect.ui.tahfidz.lajnah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.*

@Suppress("UNCHECKED_CAST")
class LajnahViewModelFactory(
    private val repository: LajnahRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LajnahViewModel(repository) as T
    }
}