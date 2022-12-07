package com.private_projects.technews.ui.allnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsDTO
import com.private_projects.technews.ui.CommonContract

class AllNewsViewModel(
    private val repository: NewsApiRepositoryImpl
) : CommonContract.CommonViewModel() {
    override fun getNews(): LiveData<PagingData<NewsDTO.Article>> {
        return repository.getNews().cachedIn(viewModelScope)
    }
}