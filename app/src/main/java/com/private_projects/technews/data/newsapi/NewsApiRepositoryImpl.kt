package com.private_projects.technews.data.newsapi

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.private_projects.technews.domain.NewsApi
import com.private_projects.technews.domain.NewsApiRepository

class NewsApiRepositoryImpl(private val newsApi: NewsApi, private val domains: String) :
    NewsApiRepository {

    override fun getNews(): LiveData<PagingData<NewsDTO.Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NewsData.PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 4
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, domains) },
            initialKey = 1
        ).liveData
    }
}