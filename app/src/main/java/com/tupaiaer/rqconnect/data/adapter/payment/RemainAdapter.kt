package com.tupaiaer.rqconnect.data.adapter.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.Article
import com.tupaiaer.rqconnect.databinding.ItemArticleBinding
import com.tupaiaer.rqconnect.ui.article.ArticleViewClickListener

class RemainAdapter(
    private val article: List<Article>,
    private val listener: ArticleViewClickListener
) : RecyclerView.Adapter<RemainAdapter.ArticleViewHolder>() {

    override fun getItemCount() = article.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        DataBindingUtil.inflate<ItemArticleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemArticleBinding.article = article[position]
        holder.itemArticleBinding.root.setOnClickListener {
            listener.onArticleViewClickListener(holder.itemArticleBinding.root, article[position])
        }
    }

    inner class ArticleViewHolder(
        val itemArticleBinding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(itemArticleBinding.root)

}