package id.credeva.rqconnect.ui.tahfidz.pekan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.credeva.rqconnect.data.repositories.PekanRepository

@Suppress("UNCHECKED_CAST")
class PekanViewModelFactory(
    private val repository: PekanRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PekanViewModel(repository) as T
    }
}