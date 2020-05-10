package com.tupaiaer.rqconnect.ui.tahfidz.triwulan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tupaiaer.rqconnect.data.repositories.*

@Suppress("UNCHECKED_CAST")
class TriwulanViewModelFactory(
    private val repository: TriwulanRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TriwulanViewModel(repository) as T
    }
}