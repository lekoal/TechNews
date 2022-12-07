package com.private_projects.technews.ui.main

import android.app.Application
import com.private_projects.technews.di.allNewsModule
import com.private_projects.technews.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(allNewsModule, mainModule)
        }
    }
}