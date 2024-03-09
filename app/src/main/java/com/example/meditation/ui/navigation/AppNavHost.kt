package com.example.meditation.ui.navigation

import android.os.Build
import android.util.Log
import android.widget.Toast
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
import com.example.meditation.MainActivity
import com.example.meditation.ui.components.musicList
import com.example.meditation.ui.screen.HistoryScreen
import com.example.meditation.ui.screen.HomeScreen
import com.example.meditation.ui.screen.LoginScreen
import com.example.meditation.ui.screen.SettingScreen
import com.example.meditation.ui.screen.SignUpScreen
import com.example.meditation.ui.screen.SplashScreen
import com.example.meditation.ui.viewmodel.HomeViewModel
import com.example.meditation.ui.viewmodel.LoginViewModel
import com.example.meditation.ui.viewmodel.SettingViewModel
import com.example.meditation.ui.viewmodel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

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
    signUpViewModel: SignUpViewModel,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    settingViewModel: SettingViewModel,
) {
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(route = Destinations.SPLASH_ROUTE) {
            SplashScreen {
                navController.navigate(Destinations.LOGIN_ROUTE) {
                    popUpTo(Destinations.SPLASH_ROUTE) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Destinations.LOGIN_ROUTE) {
            val result by loginViewModel.result.collectAsState()
            LoginScreen(
                onLogin = {
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            loginViewModel.onLoginPressed()
                            Log.d("DEBUG", "login navHost $result")
                        }
                        if (result) {
                            navController.navigate(Destinations.HOME_ROUTE)
                        } else {
                            Toast.makeText(
                                MainActivity.appContext,
                                "用户名不存在或密码错误",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        loginViewModel.cleanSignUpState()
                    }
                },
                navigateToSignUp = { navController.navigate(Destinations.SIGNUP_ROUTE) },
                loginViewModel = loginViewModel
            )
        }

        composable(route = Destinations.SIGNUP_ROUTE) {
            val result by signUpViewModel.result.collectAsState()
            SignUpScreen(
                onSignUp = {
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            signUpViewModel.onSignUpPressed()
                        }
                        Log.d("DEBUG", "result value in navHost $result")
                        if (result) {
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                MainActivity.appContext,
                                "用户名已存在",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        signUpViewModel.cleanSignUpState()
                    }
                }, signUpViewModel = signUpViewModel
            )
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
            HomeScreen(navigateToSetting = {
                navController.navigate(Destinations.SETTING_ROUTE)
            }, homeViewModel = homeViewModel, navigateToHistory = {
                navController.navigate(Destinations.HISTORY_ROUTE)
            })
        }
        composable(route = Destinations.SETTING_ROUTE) {
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

        fun test() {}
    }
}


