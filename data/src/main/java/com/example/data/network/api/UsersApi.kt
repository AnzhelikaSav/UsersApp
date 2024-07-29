package com.example.data.network.api

import com.example.data.network.models.UserListNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int = 10): UserListNetwork
}