package com.example.mediation.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mediation.R
import com.example.mediation.ui.components.Music
import com.example.mediation.ui.components.MusicList
import com.example.mediation.ui.components.TimeSetting
import com.example.mediation.ui.components.musicList
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.background_color
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.viewmodel.SettingViewModel
import com.example.mediation.ui.viewmodel.SettingViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    musicList: List<Music>,
    settingViewModel: SettingViewModel,
    navigateToHome: () -> Unit
) {
    val currentMusicIndex by settingViewModel.musicIndex.collectAsState()
    val currentHour by settingViewModel.hour.collectAsState()
    val currentMinute by settingViewModel.minute.collectAsState()
    val currentSecond by settingViewModel.second.collectAsState()
    Surface(modifier = modifier.fillMaxSize(), color = background_color) {
        val context = LocalContext.current
        Scaffold(
            topBar = { TopBackHandlerBar(backToHome = backToHome) },
            bottomBar = { BottomNavigationBar() },
            containerColor = Color.Transparent,
        ) { innerPadding ->
            Column(
                modifier = modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimeSetting(
                    onHourChange = { settingViewModel.onHourChange(it) },
                    onMinuteChange = { settingViewModel.onMinuteChange(it) },
                    onSecondChange = { settingViewModel.onSecondChange(it) },
                    hour = currentHour,
                    minute = currentMinute,
                    second = currentSecond
                )
                MusicList(
                    musicList = musicList,
                    onClick = {
                        val index = musicList.indexOf(it) + 1
                        Toast.makeText(
                            context,
                            "已加入音乐$index",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (index != currentMusicIndex) {
                            settingViewModel.onMusicChange(index)
                        }
                    }
                )
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ConfirmButton(
                        onConfirmPressed = {
                            settingViewModel.onConfirmPressed(navigateToHome = navigateToHome)
                        }
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBackHandlerBar(modifier: Modifier = Modifier, backToHome: () -> Unit) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent,
        titleContentColor = Color.Transparent
    ), title = { Text(text = "") },
        navigationIcon = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = backToHome) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrowback_icon),
                        contentDescription = "",
                        tint = icon_dark_color
                    )
                }
                Text(
                    text = "设定",
                    color = icon_dark_color,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier.offset(x = (-4).dp)
                )
            }
        }
    )
}

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onConfirmPressed: () -> Unit
) {
    Button(
        onClick = onConfirmPressed,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(icon_color),
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = "准备开始", color = Color.White)
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    MediationTheme {
        Surface {
            val settingViewModel: SettingViewModel = viewModel(factory = SettingViewModelFactory())
            SettingScreen(
                backToHome = { },
                musicList = musicList,
                settingViewModel = settingViewModel,
                navigateToHome = { }
            )
        }
    }
}