package com.private_projects.technews.di

import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsData
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.threednews.TDNewsFragment
import com.private_projects.technews.ui.threednews.TDNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tDNewsModule = module {
    single(named("tdnews_domain")) { NewsData.TDNEWS_DOMAIN }
    factory<NewsApiRepository>(named("tdnews_api_repository")) {
        NewsApiRepositoryImpl(get(named("news_api")), get(named("tdnews_domain")))
    }
    scope<TDNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("tdnews_view_model")) {
            TDNewsViewModel(get(named("tdnews_api_repository")))
        }
    }
}