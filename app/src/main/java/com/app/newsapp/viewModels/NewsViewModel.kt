package com.app.newsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.newsapp.model.remote.Article
import com.app.newsapp.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {

    fun getPageData() = newsRepo.getPageList()

    fun newsDB(query: String): LiveData<List<Article>> {
        return newsRepo.getData(query)
    }


    fun getDbAgain(): LiveData<List<Article>> {
        return newsRepo.getDataAll()
    }

    fun deleteAll() {
        newsRepo.deleteAll()
    }
}