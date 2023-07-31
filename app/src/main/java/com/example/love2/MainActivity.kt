package com.example.love2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.love2.presentation.ScreenNav
import com.example.love2.presentation.driver_screen.components.DriverScreen
import com.example.love2.presentation.routes_screen.components.RouteDetailScreen
import com.example.love2.presentation.ui.theme.Love2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Love2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = ScreenNav.DriverScreen.route) {
                        composable(ScreenNav.DriverScreen.route) { DriverScreen(navController = navController) }
                        composable("${ScreenNav.RouteScreen.route}/{driverId}") { backStackEntry ->
                            val driverId = backStackEntry.arguments?.getString("driverId")
                            RouteDetailScreen(navController = navController, driverId = driverId?.toInt())
                        }
                    }
                }
            }
        }
    }
}

