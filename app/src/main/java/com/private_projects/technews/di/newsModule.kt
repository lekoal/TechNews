package com.private_projects.technews.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.private_projects.technews.data.newsapi.NewsApiRepository
import com.private_projects.technews.data.newsapi.NewsData
import com.private_projects.technews.domain.NewsApi
import com.private_projects.technews.ui.allnews.AllNewsFragment
import com.private_projects.technews.ui.allnews.AllNewsViewModel
import com.private_projects.technews.ui.main.MainActivity
import com.private_projects.technews.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsModule = module {
    single(named("base_url")) { NewsData.BASE_URL }
    single<Retrofit>(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory(named("news_api")) {
        get<Retrofit>(named("retrofit")).create(NewsApi::class.java)
    }
    factory(named("news_api_repository")) {
        NewsApiRepository(get(named("news_api")))
    }
    scope<AllNewsFragment> {
        viewModel(named("all_news_view_model")) {
            AllNewsViewModel(get(named("news_api_repository")))
        }
    }
    scope<MainActivity> {
        viewModel(named("main_view_model")) {
            MainViewModel()
        }
    }
}