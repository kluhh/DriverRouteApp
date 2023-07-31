package com.example.love2.data.remote.dto


import com.example.love2.domain.model.DriverItem
import com.google.gson.annotations.SerializedName

data class DriverItemDto(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = ""
)




fun DriverItemDto.toDriverItem(): DriverItem {
    return DriverItem(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}



