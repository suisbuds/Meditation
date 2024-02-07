package com.example.mediation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mediation.ui.screen.HomeScreen
import com.example.mediation.ui.theme.MediationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MediationTheme {
                HomeScreen()
            }
        }
    }
}

