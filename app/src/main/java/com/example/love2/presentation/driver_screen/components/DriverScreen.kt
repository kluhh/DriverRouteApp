package com.example.love2.presentation.driver_screen.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.love2.presentation.ScreenNav
import com.example.love2.presentation.driver_screen.DriverScreenViewModel


@Composable
fun DriverScreen(
    navController: NavController,
    viewModel: DriverScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Log.d("MainScreen", "State: $state")

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.drivers?.filterNotNull() ?: listOf()) { driver ->
                DriverListItem(driverItem = driver) { selectedDriver ->
                    navController.navigate("${ScreenNav.RouteScreen.route}/${selectedDriver.id}/${selectedDriver.name}")
                }
            }
        }
        if(state.error.isNotEmpty()){
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .align(Alignment.Center)
            )
        }

    }


}