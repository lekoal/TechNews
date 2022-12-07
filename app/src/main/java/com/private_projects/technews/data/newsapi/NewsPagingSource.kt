package com.private_projects.technews.data.newsapi

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.private_projects.technews.domain.NewsApi

class NewsPagingSource(private val newsApi: NewsApi, private val domains: String) :
    PagingSource<Int, NewsDTO.Article>() {
    override fun getRefreshKey(state: PagingState<Int, NewsDTO.Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsDTO.Article> {
        return try {
            val position = params.key ?: 1
            val response = newsApi.getNews(
                domains = domains,
                page = position
            )
            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position == 1) null
                else position - 1,
                nextKey = position + 1
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }

}