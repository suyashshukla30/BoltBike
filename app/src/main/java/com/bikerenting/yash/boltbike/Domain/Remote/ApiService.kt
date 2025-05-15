package com.bikerenting.yash.boltbike.Domain.Remote

import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/getBikeList")
    suspend fun getAllBikes(): Response<List<Vehicle>>
}