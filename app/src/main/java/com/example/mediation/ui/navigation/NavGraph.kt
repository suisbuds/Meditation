package com.example.mediation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediation.ui.screen.HomeScreen
import com.example.mediation.ui.screen.SettingScreen

object Destinations {
    const val HOME_ROUTE = "home"
    const val SETTING_ROUTE = "setting"
}

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.HOME_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(route = Destinations.HOME_ROUTE) {
            HomeScreen()
        }
        composable(route = Destinations.SETTING_ROUTE) {
            SettingScreen()
        }
    }
}