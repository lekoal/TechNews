package com.private_projects.technews.di

import com.private_projects.technews.data.newsapi.NewsApiRepositoryImpl
import com.private_projects.technews.data.newsapi.NewsData
import com.private_projects.technews.domain.NewsApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.ferra.FerraNewsFragment
import com.private_projects.technews.ui.ferra.FerraNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ferraNewsModule = module {
    single(named("ferra_domain")) { NewsData.FERRA_DOMAIN }
    factory<NewsApiRepository>(named("ferra_news_api_repository")) {
        NewsApiRepositoryImpl(get(named("news_api")), get(named("ferra_domain")))
    }
    scope<FerraNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("ferra_news_view_model")) {
            FerraNewsViewModel(get(named("ferra_news_api_repository")))
        }
    }
}