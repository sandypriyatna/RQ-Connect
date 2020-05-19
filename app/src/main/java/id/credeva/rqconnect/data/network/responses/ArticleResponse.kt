package id.credeva.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import id.credeva.rqconnect.data.db.entities.Article

data class ArticleResponse(
    @SerializedName("data")
    val data: List<Article>?
)