package id.credeva.rqconnect.ui.article

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.adapter.ArticleAdapter
import id.credeva.rqconnect.data.db.entities.Article
import id.credeva.rqconnect.databinding.ActivityArticleBinding
import kotlinx.android.synthetic.main.activity_article.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception

class ArticleActivity : AppCompatActivity(), KodeinAware, ArticleViewClickListener {

    override val kodein by kodein()
    private val factory: ArticleViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityArticleBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_article)
        val viewModel = ViewModelProviders.of(this, factory).get(ArticleViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.getArticle()
        viewModel.article.observe(this, Observer { article ->
            try {
                rv_article.also {
                    it.layoutManager = LinearLayoutManager(this)
                    it.setHasFixedSize(true)
                    it.adapter = ArticleAdapter(article, this)
                }
            } catch (e: Exception) {
                Log.v("errorArticle: ", e.message)
            }
        })

        iv_back.setOnClickListener { onBackPressed() }
    }

    override fun onArticleViewClickListener(view: View, article: Article) {
        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("author", article.author)
            bundle.putString("title", article.title)
            bundle.putString("banner", article.banner)
            bundle.putString("blog", article.blog)
            val intent = Intent(this@ArticleActivity, DetailArticleActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}
