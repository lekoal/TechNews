package com.private_projects.technews.di

import com.private_projects.technews.data.vkdata.VkApiRepositoryImpl
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.ferra.FerraNewsFragment
import com.private_projects.technews.ui.ferra.FerraNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ferraNewsModule = module {
    factory<VkApiRepository>(named("ferra_news_api_repository")) {
        VkApiRepositoryImpl(get(named("news_api")), VkHelpData.FERRA_ID)
    }
    scope<FerraNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("ferra_news_view_model")) {
            FerraNewsViewModel(get(named("ferra_news_api_repository")))
        }
    }
}