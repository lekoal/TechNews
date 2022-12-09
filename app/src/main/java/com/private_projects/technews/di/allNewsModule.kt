package com.private_projects.technews.di

import com.private_projects.technews.data.vkdata.VkApiRepositoryImpl
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.allnews.AllNewsFragment
import com.private_projects.technews.ui.allnews.AllNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val allNewsModule = module {
    factory<VkApiRepository>(named("all_news_api_repository")) {
        VkApiRepositoryImpl(get(named("news_api")), VkHelpData.IXBT_ID)
    }
    scope<AllNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("all_news_view_model")) {
            AllNewsViewModel(get(named("all_news_api_repository")))
        }
    }
}