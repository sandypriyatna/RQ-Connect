package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Article
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class ArticleRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val article = MutableLiveData<List<Article>>()

    suspend fun getArticle() = apiRequest { api.getArticle() }

}