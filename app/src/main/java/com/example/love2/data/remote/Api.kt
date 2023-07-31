package com.example.love2.data.remote

import com.example.love2.data.remote.dto.DriverItemDto
import com.example.love2.data.remote.dto.ServerResponse
import retrofit2.http.GET

interface Api {
    @GET("data")
    suspend fun getDrivers(): ServerResponse

    @GET("data")
    suspend fun getRoutes(): ServerResponse

}