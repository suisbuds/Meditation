package com.example.mediation.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediation.ui.screen.HomeScreen
import com.example.mediation.ui.screen.SettingScreen
import com.example.mediation.ui.viewmodel.HomeViewModel

object Destinations {
    const val HOME_ROUTE = "home"
    const val SETTING_ROUTE = "setting"
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.HOME_ROUTE,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(route = Destinations.HOME_ROUTE) {
            HomeScreen(navigateToSetting = {
                navController.navigate(Destinations.SETTING_ROUTE)
            }, homeViewModel = homeViewModel)
        }
        composable(route = Destinations.SETTING_ROUTE, enterTransition = {
            fadeIn(
                animationSpec = tween(
                    250, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(250, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }) {
            SettingScreen(backToHome = { navController.popBackStack() })
        }
    }
}


