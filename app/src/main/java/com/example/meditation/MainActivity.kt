package com.example.meditation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meditation.ui.navigation.AppNavHost
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.viewmodel.HomeViewModel
import com.example.meditation.ui.viewmodel.SettingViewModel
import com.example.meditation.ui.viewmodel.SettingViewModelFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                //注入模型
                val homeViewModel: HomeViewModel by viewModels()
                val settingViewModel: SettingViewModel =
                    viewModel(factory = SettingViewModelFactory())
                AppNavHost(homeViewModel = homeViewModel, settingViewModel = settingViewModel)
            }
            /*MediationTheme {
                HistoryScreen()
            }*/
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}

