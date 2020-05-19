package id.credeva.rqconnect.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.db.entities.Article
import id.credeva.rqconnect.data.repositories.ArticleRepository
import id.credeva.rqconnect.util.Coroutines
import kotlinx.coroutines.Job

class ArticleViewModel(
    private val repository: ArticleRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>>
        get() = _article

    fun getArticle() {
        job = Coroutines.ioThenMain(
            { repository.getArticle() },
            { _article.value = it!!.data }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}