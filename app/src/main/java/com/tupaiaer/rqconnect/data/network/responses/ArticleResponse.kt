package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Article

data class ArticleResponse(
    @SerializedName("data")
    val data: List<Article>?
)