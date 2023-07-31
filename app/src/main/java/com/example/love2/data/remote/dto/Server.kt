package com.example.love2.data.remote.dto

data class ServerResponse(
    val drivers: List<DriverItemDto>,
    val routes: List<RouteItemDto>
)
