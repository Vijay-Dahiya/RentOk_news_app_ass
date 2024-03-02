package com.app.newsapp.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.databinding.ItemLayoutBinding
import com.app.newsapp.model.remote.Article
import com.bumptech.glide.Glide

class NewsPageAdapter : PagingDataAdapter<Article, NewsPageHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(
                oldItem: Article,
                newItem: Article,
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Article,
                newItem: Article,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: NewsPageHolder, position: Int) {
        val result = getItem(position)
        result?.let {
            holder.setData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPageHolder {
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout, parent, false
            )
        return NewsPageHolder(itemLayoutBinding)
    }
}

class NewsPageHolder(
    private var itemLayoutBinding: ItemLayoutBinding,
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(article: Article) {
        Glide.with(itemLayoutBinding.ivNewsImage).load(article.urlToImage).into(itemLayoutBinding.ivNewsImage)
        itemLayoutBinding.newsData = article
    }

}
