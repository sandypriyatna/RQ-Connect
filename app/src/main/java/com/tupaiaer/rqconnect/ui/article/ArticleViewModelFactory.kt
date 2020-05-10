package com.tupaiaer.rqconnect.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tupaiaer.rqconnect.data.repositories.ArticleRepository

@Suppress("UNCHECKED_CAST")
class ArticleViewModelFactory(
    private val repository: ArticleRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(repository) as T
    }
}