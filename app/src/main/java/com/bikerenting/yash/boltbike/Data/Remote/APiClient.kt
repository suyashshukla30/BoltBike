package com.bikerenting.yash.boltbike.Data.Remote

import com.google.firebase.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private const val IS_DEBUG_BUILD = true

    private const val BASE_URL_DEBUG = "http://192.168.1.5:8080"
    private const val BASE_URL_PROD = "https://boltbike-backend.onrender.com"
    private val BASE_URL : String = if(IS_DEBUG_BUILD){
        BASE_URL_DEBUG
    }else{
        BASE_URL_PROD
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}