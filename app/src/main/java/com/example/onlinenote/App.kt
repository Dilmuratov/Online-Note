package com.example.onlinenote

import android.app.Application
import com.example.onlinenote.di.appModule
import com.example.onlinenote.di.dataModule
import com.example.onlinenote.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(appModule, dataModule, networkModule))
            androidContext(this@App)
        }
    }
}