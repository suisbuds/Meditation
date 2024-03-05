package com.example.meditation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.meditation.data.db.MessageDatabase
import com.example.meditation.ui.navigation.AppNavHost
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.viewmodel.HomeViewModel
import com.example.meditation.ui.viewmodel.HomeViewModelFactory
import com.example.meditation.ui.viewmodel.SettingViewModel
import com.example.meditation.ui.viewmodel.SettingViewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth;

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        enableEdgeToEdge()

        auth= Firebase.auth

        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                //注入模型
                val settingViewModel: SettingViewModel =
                    viewModel(factory = SettingViewModelFactory())
                val database by lazy {
                    Room.databaseBuilder(
                        appContext,
                        klass = MessageDatabase::class.java,
                        name = "message.db"
                    ).build()
                }
                val homeViewModel: HomeViewModel =
                    viewModel(factory = HomeViewModelFactory(database.messageDao))
                AppNavHost(homeViewModel = homeViewModel, settingViewModel = settingViewModel, auth = auth)
            }

        }
    }

    /*override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            TODO()
        }
    }*/


    companion object {
        lateinit var appContext: Context
    }
}

