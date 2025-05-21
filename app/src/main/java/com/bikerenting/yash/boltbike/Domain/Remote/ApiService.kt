package com.bikerenting.yash.boltbike.Domain.Remote

import com.bikerenting.yash.boltbike.Domain.Model.Vehicle
import com.bikerenting.yash.boltbike.Presentation.ui.UserRequest
import com.bikerenting.yash.boltbike.Domain.Model.Response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("/getBikeList")
    suspend fun getAllBikes(): Response<List<Vehicle>>

    @GET("/getAvailableProfileFeature")
    suspend fun getAvailableProfileFeature(): Response<List<Boolean>>

    @POST("/registerNewUser")
    suspend fun registerNewUser(
        @Header("Authorization") token: String,
        @Body user: UserRequest
    ): Response<RegisterResponse>
}