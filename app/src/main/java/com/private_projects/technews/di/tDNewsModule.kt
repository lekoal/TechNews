package com.private_projects.technews.di

import com.private_projects.technews.data.vkdata.VkApiRepositoryImpl
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.threednews.TDNewsFragment
import com.private_projects.technews.ui.threednews.TDNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tDNewsModule = module {
    factory<VkApiRepository>(named("tdnews_api_repository")) {
        VkApiRepositoryImpl(get(named("news_api")), VkHelpData.TDNEWS_ID)
    }
    scope<TDNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("tdnews_view_model")) {
            TDNewsViewModel(get(named("tdnews_api_repository")))
        }
    }
}