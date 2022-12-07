package com.private_projects.technews.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.private_projects.technews.data.newsapi.NewsDTO

interface NewsApiRepository {
    fun getNews(): LiveData<PagingData<NewsDTO.Article>>
}