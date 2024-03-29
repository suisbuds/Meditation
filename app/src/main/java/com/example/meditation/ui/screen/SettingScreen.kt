package com.example.meditation.ui.screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.R
import com.example.meditation.ui.components.Music
import com.example.meditation.ui.components.MusicList
import com.example.meditation.ui.components.TimeSetting
import com.example.meditation.ui.theme.background_color
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.viewmodel.SettingViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    musicList: List<Music>,
    settingViewModel: SettingViewModel,
    navigateToHome: () -> Unit,
    colorIndex: Int = 0
) {
    val currentMusicIndex by settingViewModel.musicIndex.collectAsState()
    val currentHour by settingViewModel.hour.collectAsState()
    val currentMinute by settingViewModel.minute.collectAsState()
    val currentSecond by settingViewModel.second.collectAsState()
    Surface(modifier = modifier.fillMaxSize(), color = background_color) {
        val context = LocalContext.current
        Scaffold(
            topBar = { TopBackHandlerBar(backToHome = backToHome, topAppBarName = "设定") },
            bottomBar = { BottomNavigationBar(colorIndex = colorIndex) },
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
                    currentMusicIndex = currentMusicIndex,
                    onClick = {
                        val index = musicList.indexOf(it)
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
                            if (currentHour == 0 && currentMinute == 0 && currentSecond == 0) Toast.makeText(
                                context,
                                "请设置时间",
                                Toast.LENGTH_SHORT
                            ).show()
                            else if (currentMusicIndex == -1) Toast.makeText(
                                context,
                                "请设置背景音乐",
                                Toast.LENGTH_SHORT
                            ).show()
                            else settingViewModel.onConfirmPressed(navigateToHome = navigateToHome)
                        }
                    )
                }

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBackHandlerBar(modifier: Modifier = Modifier, backToHome: () -> Unit, topAppBarName: String) {
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
                        tint = icon_dark_color_brown
                    )
                }
                Text(
                    text = topAppBarName,
                    color = icon_dark_color_brown,
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
        colors = ButtonDefaults.buttonColors(icon_color_brown),
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = "准备开始", color = Color.White)
    }
}

