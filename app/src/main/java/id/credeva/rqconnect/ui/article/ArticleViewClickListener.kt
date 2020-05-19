package id.credeva.rqconnect.ui.article

import android.view.View
import id.credeva.rqconnect.data.db.entities.Article

interface ArticleViewClickListener {
    fun onArticleViewClickListener(view: View, article: Article)
}