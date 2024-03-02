package com.app.newsapp.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.databinding.ItemLayoutBinding
import com.app.newsapp.model.remote.Article
import com.bumptech.glide.Glide

class NewsAdapter(private val list: List<Article>) : RecyclerView.Adapter<NewsHolderData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolderData {
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_layout, parent, false)
        return NewsHolderData(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: NewsHolderData, position: Int) {
        val newsList = list[position]
        holder.setData(newsList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class NewsHolderData(
    private val itemLayoutBinding: ItemLayoutBinding
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(article: Article) {
        Glide.with(itemLayoutBinding.ivNewsImage).load(article.urlToImage).into(itemLayoutBinding.ivNewsImage)
        itemLayoutBinding.newsData = article
    }
}
