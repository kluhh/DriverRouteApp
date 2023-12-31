package com.example.love2.presentation.routes_screen.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.love2.presentation.routes_screen.RouteScreenViewModel

@Composable
fun RouteDetailScreen(
    navController: NavController,
    driverId: Int?,
    driverName: String?,
    viewModel: RouteScreenViewModel = hiltViewModel()
) {

    // Call the function to set the route when the screen is opened
    LaunchedEffect(driverId) {
        if (driverId != null) {
            Log.d("RouteDetailScreen", "Calling getRouteForDriver with driverId: $driverId")

            viewModel.getRouteForDriver(driverId)
        }
    }

// Use a Box to align the content at the top
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        // Use a Card to provide elevation and rounded corners
        Card(modifier = Modifier.padding(16.dp)) {
            // Use a Column to stack Text items vertically
            Column(modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                val route = viewModel.route.value
                if (route != null) {
                    Text(
                        text = "${driverName}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Route: ${route.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // provide some spacing between the texts
                    Text(
                        text = "Type: ${route.type}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text(
                        text = "No route found for driver $driverId",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

}