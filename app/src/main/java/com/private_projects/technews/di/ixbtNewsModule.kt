package com.private_projects.technews.di

import com.private_projects.technews.data.jsoup.IxbtElementReceiverImpl
import com.private_projects.technews.data.vkdata.VkApiRepositoryImpl
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.domain.ElementReceiver
import com.private_projects.technews.domain.VkApiRepository
import com.private_projects.technews.ui.CommonContract
import com.private_projects.technews.ui.ixbt.IxbtNewsFragment
import com.private_projects.technews.ui.ixbt.IxbtNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ixbtNewsModule = module {
    factory<VkApiRepository>(named("ixbt_news_api_repository")) {
        VkApiRepositoryImpl(get(named("news_api")), VkHelpData.IXBT_ID)
    }
    single<ElementReceiver>(named("ixbt_element_receiver")) {
        IxbtElementReceiverImpl()
    }
    scope<IxbtNewsFragment> {
        scoped<CommonContract.CommonViewModel>(named("ixbt_news_view_model")) {
            IxbtNewsViewModel(
                get(named("ixbt_news_api_repository")),
                get(named("ixbt_element_receiver"))
            )
        }
    }
}