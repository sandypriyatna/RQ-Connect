package com.tupaiaer.rqconnect.ui.article

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tupaiaer.rqconnect.R
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)

        getDetailArticle()
    }

    private fun getDetailArticle() {
        val bundle = intent.extras
        tv_author_article.text = "ditulis oleh: ${bundle!!.getString("author")}"
        tv_title_article.text = bundle.getString("title")
        tv_title_article2.text = bundle.getString("title")
        tv_blog_article.text = bundle.getString("blog")
        tv_blog_article.movementMethod = ScrollingMovementMethod()

        Glide.with(this)
            .load(bundle.getString("banner"))
            .into(iv_banner_article)

        iv_back.setOnClickListener { onBackPressed() }
    }
}
