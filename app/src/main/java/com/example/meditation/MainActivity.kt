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
import com.example.meditation.ui.viewmodel.LoginViewModel
import com.example.meditation.ui.viewmodel.LoginViewModelFactory
import com.example.meditation.ui.viewmodel.SettingViewModel
import com.example.meditation.ui.viewmodel.SettingViewModelFactory
import com.example.meditation.ui.viewmodel.SignUpViewModel
import com.example.meditation.ui.viewmodel.SignUpViewModelFactory


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
                val loginViewModel:LoginViewModel= viewModel(factory = LoginViewModelFactory())
                val signUpViewModel:SignUpViewModel= viewModel(factory = SignUpViewModelFactory())
                val database by lazy {
                    Room.databaseBuilder(
                        appContext,
                        klass = MessageDatabase::class.java,
                        name = "message.db"
                    ).build()
                }
                val homeViewModel: HomeViewModel =
                    viewModel(factory = HomeViewModelFactory(database.messageDao))
                AppNavHost(
                    homeViewModel = homeViewModel,
                    settingViewModel = settingViewModel,
                    loginViewModel = loginViewModel,
                    signUpViewModel = signUpViewModel
                )
            }

        }
    }


    companion object {
        lateinit var appContext: Context
    }
}

