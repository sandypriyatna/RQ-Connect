package id.credeva.rqconnect.ui.article

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.credeva.rqconnect.R
import kotlinx.android.synthetic.main.activity_detail_article.*

class DetailArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)

        getDetailArticle()
    }

    private fun getDetailArticle() {
        val bundle = intent.extras
        tv_author_article.text = "dipublikasikan oleh: ${bundle!!.getString("author")}"
        tv_title_article2.text = bundle.getString("title")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_blog_article.text =
                Html.fromHtml(bundle.getString("blog"), Html.FROM_HTML_MODE_COMPACT)
        } else {
            tv_blog_article.text = Html.fromHtml(bundle.getString("blog"))
        }
        tv_blog_article.movementMethod = ScrollingMovementMethod()

        Glide.with(this)
            .load(bundle.getString("banner"))
            .into(iv_banner_article)

        iv_back.setOnClickListener { onBackPressed() }
    }
}
