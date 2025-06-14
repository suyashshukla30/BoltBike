package com.bikerenting.yash.boltbike.di

import android.app.Application
import androidx.room.Room
import com.bikerenting.yash.boltbike.Data.Local.AppDatabase
import com.bikerenting.yash.boltbike.Data.Local.DAO
import com.bikerenting.yash.boltbike.Presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide AppDatabase
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "bolt_bike_db"
        ).build()
    }

    // Provide DAO
    single<DAO> {
        get<AppDatabase>().appDao()
    }

    // Provide HomeViewModel
    viewModel {
        HomeViewModel(userDao = get())
    }
}
