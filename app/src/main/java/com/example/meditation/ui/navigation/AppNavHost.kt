package com.example.meditation.ui.navigation

import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meditation.ui.components.musicList
import com.example.meditation.ui.screen.HistoryScreen
import com.example.meditation.ui.screen.HomeScreen
import com.example.meditation.ui.screen.LoginScreen
import com.example.meditation.ui.screen.SettingScreen
import com.example.meditation.ui.screen.SignUpScreen
import com.example.meditation.ui.screen.SplashScreen
import com.example.meditation.ui.viewmodel.HomeViewModel
import com.example.meditation.ui.viewmodel.SettingViewModel
import com.google.firebase.auth.FirebaseAuth

object Destinations {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val SETTING_ROUTE = "setting"
    const val HISTORY_ROUTE = "history"
    const val LOGIN_ROUTE = "login"
    const val SIGNUP_ROUTE = "sign up"
}


@OptIn(UnstableApi::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Destinations.SPLASH_ROUTE,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel,
    settingViewModel: SettingViewModel,
    auth: FirebaseAuth
) {
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(route = Destinations.SPLASH_ROUTE) {
            SplashScreen {
                navController.navigate(Destinations.HOME_ROUTE) {
                    popUpTo(Destinations.SPLASH_ROUTE) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Destinations.LOGIN_ROUTE) {
            LoginScreen(auth = auth, onLogInPressed = {}, navigateToSignUp = {})
        }

        composable(route = Destinations.SIGNUP_ROUTE) {
            SignUpScreen(onSignUpPressed = {})
        }

        composable(route = Destinations.HOME_ROUTE) {
            val musicIndex by settingViewModel.musicIndex.collectAsState()
            val hour by settingViewModel.hour.collectAsState()
            val minute by settingViewModel.minute.collectAsState()
            val second by settingViewModel.second.collectAsState()
            if (musicIndex != -1) {
                homeViewModel.handleSettingData(
                    listOf(hour, minute, second),
                    RawResourceDataSource.buildRawResourceUri(musicList[musicIndex].musicId),
                    musicTitle = musicList[musicIndex].title
                ) { settingViewModel.onStart() }
            }
            HomeScreen(
                navigateToSetting = {
                    navController.navigate(Destinations.SETTING_ROUTE)
                },
                homeViewModel = homeViewModel,
                navigateToHistory = {
                    navController.navigate(Destinations.HISTORY_ROUTE)
                }
            )
        }
        composable(
            route = Destinations.SETTING_ROUTE/*, enterTransition = {
            fadeIn(
                animationSpec = tween(
                    200, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(250, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(
                    200, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(250, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }*/
        ) {
            SettingScreen(backToHome = { navController.popBackStack() },
                musicList = musicList,
                settingViewModel = settingViewModel,
                navigateToHome = {
                    navController.navigate(Destinations.HOME_ROUTE)
                })
        }
        composable(route = Destinations.HISTORY_ROUTE) {
            HistoryScreen(
                backToHome = { navController.popBackStack() },
                homeViewModel = homeViewModel,
            )
        }
    }
}


