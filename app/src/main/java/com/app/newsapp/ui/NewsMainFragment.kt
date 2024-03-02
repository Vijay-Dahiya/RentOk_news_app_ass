package com.app.newsapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.newsapp.R
import com.app.newsapp.databinding.FragmentNewsMainBinding
import com.app.newsapp.model.remote.Article
import com.app.newsapp.ui.adaptor.NewsAdapter
import com.app.newsapp.ui.adaptor.NewsPageAdapter
import com.app.newsapp.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsMainFragment : Fragment() {

    lateinit var newsMainBinding: FragmentNewsMainBinding
    private val newsViewModel: NewsViewModel by viewModels()
    private var newsList = mutableListOf<Article>()
    private lateinit var newsPageAdapter: NewsPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        newsMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_main, container, false)
        return newsMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsMainBinding.progressBar.visibility = View.VISIBLE
        newsPageAdapter = NewsPageAdapter()
        setAdaptor()
        loadPageData()
        searchload()

    }

    private fun loadPageData() {
        newsViewModel.deleteAll()
        newsViewModel.getPageData().observe(viewLifecycleOwner) {
            it?.let {
                CoroutineScope(Dispatchers.Main).launch {
                    newsMainBinding.progressBar.visibility = View.GONE
                    newsPageAdapter.submitData(it)
                }
            }
        }
    }

    private fun searchload() {
        newsMainBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isNotEmpty()) {
                    loadSearchData(newsMainBinding.etSearch.text.toString())
                } else {
                    newsViewModel.getDbAgain().observe(viewLifecycleOwner) {
                        newsList.clear()
                        newsList.addAll(it)
                        val adaptor = NewsAdapter(newsList)
                        val linearLayoutManager = LinearLayoutManager(context)
                        newsMainBinding.rvMainNews.apply {
                            adapter = adaptor
                            layoutManager = linearLayoutManager
                        }
                    }
                }
            }
        })
    }

    private fun loadSearchData(query: String) {

        newsViewModel.newsDB(query).observe(this) {
            newsList.clear()
            newsList.addAll(it)
            newsList.reverse()
            val adaptor = NewsAdapter(newsList)
            val linearLayoutManager = LinearLayoutManager(context)
            newsMainBinding.rvMainNews.apply {
                adapter = adaptor
                layoutManager = linearLayoutManager
            }
        }
    }

    private fun setAdaptor() {
        val linearLayoutManager = LinearLayoutManager(context)
        newsMainBinding.rvMainNews.apply {
            adapter = newsPageAdapter
            layoutManager = linearLayoutManager
        }
    }
}