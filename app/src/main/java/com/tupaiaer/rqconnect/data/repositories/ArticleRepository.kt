package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Article
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class ArticleRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val article = MutableLiveData<List<Article>>()

    suspend fun getArticle() = apiRequest { api.getArticle() }

}