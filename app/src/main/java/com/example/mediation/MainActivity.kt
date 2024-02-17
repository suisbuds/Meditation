package com.example.mediation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mediation.ui.navigation.AppNavHost
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.viewmodel.HomeViewModel
import com.example.mediation.ui.viewmodel.SettingViewModel
import com.example.mediation.ui.viewmodel.SettingViewModelFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MediationTheme {
                //注入模型
                val homeViewModel: HomeViewModel by viewModels()
                val settingViewModel: SettingViewModel =
                    viewModel(factory = SettingViewModelFactory())
                AppNavHost(homeViewModel = homeViewModel, settingViewModel = settingViewModel)
            }
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}

