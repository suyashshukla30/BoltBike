package com.bikerenting.yash.boltbike.Core

import android.app.Application
import com.bikerenting.yash.boltbike.Data.Local.AppDatabase

class MyApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getInstance(this)
    }
}