package com.private_projects.technews.ui.allnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class AllNewsViewModel(
    private val repository: NewsApiRepositoryImpl
) : ViewModel() {
    private val allNewsScope = CoroutineScope(Dispatchers.IO)

    fun getNews(): LiveData<PagingData<NewsDTO.Article>> {
        return repository.getNews().cachedIn(viewModelScope)
    }

    override fun onCleared() {
        allNewsScope.cancel()
        super.onCleared()
    }
}