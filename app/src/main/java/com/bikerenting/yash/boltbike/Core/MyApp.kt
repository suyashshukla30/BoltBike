package com.bikerenting.yash.boltbike.Core

import android.app.Application
import com.bikerenting.yash.boltbike.Data.Local.AppDatabase
import com.bikerenting.yash.boltbike.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
        database = AppDatabase.getInstance(this)
    }
}