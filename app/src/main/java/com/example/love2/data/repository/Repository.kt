package com.example.love2.data.repository

import com.example.love2.data.remote.dto.ServerResponse

interface Repository {

    suspend fun getDrivers(): ServerResponse

    suspend fun getRoute(): ServerResponse
}


