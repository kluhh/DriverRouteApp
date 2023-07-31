package com.example.love2.presentation

sealed class ScreenNav(val route: String){
    object DriverScreen: ScreenNav("driver_screen")
    object RouteScreen: ScreenNav("route_screen")
}
