package com.example.love2.data.remote.dto


import com.example.love2.domain.model.RouteItem
import com.google.gson.annotations.SerializedName

data class RouteItemDto(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("type")
    val type: String? = ""
)

fun RouteItemDto.toRouteItem(): RouteItem {
    return RouteItem(
        id = id ?: 0,
        name = name.orEmpty(),
        type = type.orEmpty()
    )
}