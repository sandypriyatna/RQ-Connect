package com.tupaiaer.rqconnect.ui.article

import android.view.View
import com.tupaiaer.rqconnect.data.db.entities.Article

interface ArticleViewClickListener {
    fun onArticleViewClickListener(view: View, article: Article)
}