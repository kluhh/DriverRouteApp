package com.example.love2.presentation.routes_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.love2.common.Resource
import com.example.love2.data.remote.dto.RouteItemDto
import com.example.love2.domain.model.RouteItem
import com.example.love2.domain.use_case.get_route.GetRouteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteScreenViewModel @Inject constructor(
    private val getRouteUseCase: GetRouteUseCase
) : ViewModel() {

    // Mutable state to hold the current route
    private val _route = mutableStateOf<RouteItem?>(null)
    // Publicly exposed immutable state for observers to listen to
    val route: State<RouteItem?> = _route

    // Function to determine the route for a driver based on their ID
    fun getRouteForDriver(driverId: Int) {
        // Log for debugging purposes
        Log.d("RouteScreenViewModel", "getRouteForDriver called with driverId: $driverId")

        // Launch a coroutine in the ViewModel's scope
        viewModelScope.launch {
            // Fetch the routes
            getRouteUseCase().onEach { result ->
                // Log for debugging purposes
                Log.d("RouteScreenViewModel", "Fetched Routes ${result}")

                // Check the result type
                if (result is Resource.Success<*>) {
                    // Cast the data to the expected type
                    val routes = result.data ?: emptyList()
                    // Log for debugging purposes
                    Log.d("RouteScreenViewModel", "Routes returned by getRouteUseCase: $routes")

                    // Determine the route based on the driver's ID
                    _route.value = when {
                        driverId == route.value?.id -> routes.firstOrNull { it.id == driverId }
                        driverId % 2 == 0 -> routes.firstOrNull { it.type == "R" }
                        driverId % 5 == 0 -> routes.firstOrNull { it.type == "C" }
                        else -> routes.lastOrNull { it.type == "I" }
                    }
                    // Log for debugging purposes
                    Log.d("RouteScreenViewModel", "Route set to: ${_route.value}")

                } else if (result is Resource.Error<*>) {
                    // Log the error message
                    Log.d("RouteScreenViewModel", "getRouteUseCase returned Error: ${result.message}")
                } else if (result is Resource.Loading<*>) {
                    // Log the loading state
                    Log.d("RouteScreenViewModel", "getRouteUseCase returned Loading")
                }
            }.launchIn(this)
        }
    }
}
