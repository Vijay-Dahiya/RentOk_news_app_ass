package com.app.newsapp.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.newsapp.model.remote.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(article: List<Article>)

    @Query("select * from article where source like :query")
    fun getDb(query: String): LiveData<List<Article>>

    @Query("select * from article")
    fun getDbAgain(): LiveData<List<Article>>

    @Query("DELETE FROM Article")
    suspend fun deleteAll()
}