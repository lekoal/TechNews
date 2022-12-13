package com.private_projects.technews.di

import com.private_projects.technews.data.jsoup.IxbtElementReceiverImpl
import com.private_projects.technews.ui.ixbt.VkIxbtPagerAdapter
import com.private_projects.technews.domain.ElementReceiver
import com.private_projects.technews.ui.ixbt.IxbtNewsFragment
import com.private_projects.technews.ui.ixbt.IxbtNewsViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ixbtNewsModule = module {
    single<ElementReceiver>(named("ixbt_element_receiver")) {
        IxbtElementReceiverImpl()
    }
    scope<IxbtNewsFragment> {
        scoped(named("ixbt_view_model")) {
            IxbtNewsViewModel(get(named("news_api_repository")))
        }
        scoped(named("ixbt_adapter")) {
            VkIxbtPagerAdapter()
        }
    }
}