package com.app.newsapp.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.app.newsapp.model.local.NewsDao
import com.app.newsapp.model.local.ResultPagingSource
import com.app.newsapp.model.remote.ApiClient
import com.app.newsapp.model.remote.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsRepo @Inject constructor(val apiClient: ApiClient, val newsDao: NewsDao) {

    fun getPageList() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = { ResultPagingSource(newsDao) }
        ).liveData

    fun getData(query: String): LiveData<List<Article>> {
        return newsDao.getDb(query)
    }

    fun getDataAll(): LiveData<List<Article>> {
        return newsDao.getDbAgain()
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.deleteAll()
        }
    }
}