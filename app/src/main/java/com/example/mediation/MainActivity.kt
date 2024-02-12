package com.example.mediation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.mediation.ui.navigation.AppNavHost
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MediationTheme {
                //注入模型
                val homeViewModel: HomeViewModel by viewModels()
                AppNavHost(homeViewModel = homeViewModel)
            }
        }
    }
}

