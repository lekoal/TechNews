package com.private_projects.technews.data.newsapi

import com.private_projects.technews.domain.NewsApi

class NewsApiRepository(private val newsApi: NewsApi) {
    suspend fun getNews(domains: String, sorting: String) = newsApi.getNews(domains, sorting)
}