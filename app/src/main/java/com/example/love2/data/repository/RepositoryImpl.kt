package com.example.love2.data.repository

import com.example.love2.data.remote.Api
import com.example.love2.data.remote.dto.DriverItemDto
import com.example.love2.data.remote.dto.ServerResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api ): Repository {
    override suspend fun getDrivers(): ServerResponse {
        return api.getDrivers()
    }

    override suspend fun getRoute(): ServerResponse {
        return api.getRoutes()
    }
}