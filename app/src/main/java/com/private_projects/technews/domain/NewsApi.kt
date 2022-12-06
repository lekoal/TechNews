package com.private_projects.technews.domain

import com.private_projects.technews.data.newsapi.NewsDTO
import com.private_projects.technews.data.newsapi.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything?language=${NewsData.LANG}")
    suspend fun getNews(
        @Query("domains") domains: String,
        @Query("sortBy") sorting: String,
        @Query("pageSize") pageSize: Int = NewsData.PAGE_SIZE,
        @Query("apiKey") apiKey: String = NewsData.NEWS_API_KEY
    ): Response<NewsDTO>
}