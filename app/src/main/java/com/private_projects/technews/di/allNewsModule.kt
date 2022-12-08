package com.private_projects.technews.di

import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsData
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.allnews.AllNewsFragment
import com.private_projects.technews.ui.allnews.AllNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val allNewsModule = module {
    single(named("all_domains")) { NewsData.DOMAINS }
    factory<NewsApiRepository>(named("all_news_api_repository")) {
        NewsApiRepositoryImpl(get(named("news_api")), get(named("all_domains")))
    }
    scope<AllNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("all_news_view_model")) {
            AllNewsViewModel(get(named("all_news_api_repository")))
        }
    }
}