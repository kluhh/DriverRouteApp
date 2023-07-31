package com.example.love2.presentation.driver_screen

import com.example.love2.domain.use_case.get_driver.GetDriveUseCase
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.love2.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriverScreenViewModel @Inject constructor(
    private val getDriveUseCase: GetDriveUseCase
) : ViewModel() {

    // Mutable state to hold the current screen state
    private val _state = mutableStateOf(DriverScreenState())
    // Publicly exposed immutable state for observers to listen to
    val state: State<DriverScreenState> = _state

    // Initializer block to get driver data when ViewModel is created
    init {
        getDriver()
    }

    // Private function to initiate the driver data fetch
    private fun getDriver() {
        // Call the usecase and handle each emitted result
        getDriveUseCase().onEach { result ->
            when (result) {
                // On success, update the state with the new driver list
                is Resource.Success -> {
                    _state.value = _state.value.copy(drivers = result.data ?: emptyList())
                }
                // On error, update the state with the error message
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message ?: "Unexpected error")
                }
                // On loading, update the state to show loading status
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
            // Launch the collection of the flow in the ViewModel's scope
        }.launchIn(viewModelScope)
    }

}
