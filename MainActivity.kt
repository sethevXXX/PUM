package com.example.degreemanagment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.degreemanagment.ui.theme.DegreeManagmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DegreeManagmentTheme {
                Surface() {
                    StartApp()
                }
            }
        }
    }
}

// NAVIGATION
@Composable
fun StartApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController = navController)
        }
        composable("secondScreen") {
            SecondScreen(navController = navController)
        }
        composable("thirdScreen/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: -1
            ThirdScreen(navController = navController, itemId = itemId)
        }
    }
}
