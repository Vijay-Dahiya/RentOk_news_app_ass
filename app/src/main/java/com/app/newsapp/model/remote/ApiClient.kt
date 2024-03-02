package com.app.newsapp.model.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface ApiClient {
    /**
     * https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=32bfcea9923a439dbadbcb6d184c3730
     */

    @GET("v2/top-headlines?country=in&category=entertainment&apiKey=32bfcea9923a439dbadbcb6d184c3730")
    suspend fun getData(
        @Query("page") page: Int
    ): ResponseDTO
}