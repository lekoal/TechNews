package com.private_projects.technews.ui.allnews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.private_projects.technews.data.newsapi.NewsApiRepository
import com.private_projects.technews.data.newsapi.NewsDTO
import com.private_projects.technews.data.newsapi.NewsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AllNewsViewModel(
    private val repository: NewsApiRepository
) : ViewModel() {
    private val allNewsScope = CoroutineScope(Dispatchers.IO)
    val result = MutableLiveData<NewsDTO>()

    fun getNews() {
        allNewsScope.launch {
            val response =  repository.getNews(NewsData.DOMAINS, NewsData.SORT_PUBLISHED)
            if (response.isSuccessful) {
                result.postValue(response.body())
            } else {
                Log.e("ANVM_ERROR", response.message())
            }
        }
    }

    override fun onCleared() {
        allNewsScope.cancel()
        super.onCleared()
    }
}