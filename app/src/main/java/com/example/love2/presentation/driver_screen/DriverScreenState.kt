package com.example.love2.presentation.driver_screen

import com.example.love2.domain.model.DriverItem


data class DriverScreenState(
    val isLoading: Boolean = false,
    val drivers: List<DriverItem?>? = emptyList(),
    val error: String = ""
)