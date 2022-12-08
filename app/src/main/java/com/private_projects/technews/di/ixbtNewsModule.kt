package com.private_projects.technews.di

import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsData
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.ixbt.IxbtNewsFragment
import com.private_projects.technews.ui.ixbt.IxbtNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ixbtNewsModule = module {
    single(named("ixbt_domain")) { NewsData.IXBT_DOMAIN }
    factory<NewsApiRepository>(named("ixbt_news_api_repository")) {
        NewsApiRepositoryImpl(get(named("news_api")), get(named("ixbt_domain")))
    }
    scope<IxbtNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("ixbt_news_view_model")) {
            IxbtNewsViewModel(get(named("ixbt_news_api_repository")))
        }
    }
}