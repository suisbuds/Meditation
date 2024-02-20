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

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                //注入模型
                val settingViewModel: SettingViewModel =
                    viewModel(factory = SettingViewModelFactory())
                val database by lazy {
                    Room.databaseBuilder(
                        applicationContext,
                        klass = MessageDatabase::class.java,
                        name = "message.db"
                    ).build()
                }
                val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(database.messageDao))
                AppNavHost(homeViewModel = homeViewModel, settingViewModel = settingViewModel)
            }
/*
            MeditationTheme {
                val database by lazy {
                    Room.databaseBuilder(
                        applicationContext,
                        klass = MessageDatabase::class.java,
                        name = "message.db"
                    ).build()
                }
                val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(database.messageDao))
                val historyMessages by homeViewModel.historyMessages.collectAsState()
                HistoryScreen(messages = historyMessages)
            }
*/
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}

