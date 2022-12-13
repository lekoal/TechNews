package com.private_projects.technews.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.private_projects.technews.data.vkdata.ixbt.VkIxbtApiRepositoryImpl
import com.private_projects.technews.data.vkdata.VkHelpData
import com.private_projects.technews.domain.ixbt.VkIxbtApi
import com.private_projects.technews.domain.ixbt.VkIxbtApiRepository
import com.private_projects.technews.ui.main.MainActivity
import com.private_projects.technews.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single(named("base_url")) { VkHelpData.BASE_URL }
    factory<VkIxbtApiRepository>(named("news_api_repository")) {
        VkIxbtApiRepositoryImpl(get(named("news_api")))
    }
    factory<Retrofit>(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory(named("news_api")) {
        get<Retrofit>(named("retrofit")).create(VkIxbtApi::class.java)
    }
    scope<MainActivity> {
        viewModel(named("main_view_model")) {
            MainViewModel()
        }
    }
}