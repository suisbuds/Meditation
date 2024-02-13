package com.example.mediation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.ui.components.MusicList
import com.example.mediation.ui.components.TimeSetting
import com.example.mediation.ui.components.musicList
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.background_color
import com.example.mediation.ui.theme.icon_dark_color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(modifier: Modifier = Modifier, backToHome: () -> Unit) {
    Surface(modifier = modifier.fillMaxSize(), color = background_color) {
        val hour by remember {
            mutableIntStateOf(0)
        }
        val minute by remember {
            mutableIntStateOf(0)
        }
        val second by remember {
            mutableIntStateOf(0)
        }
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
                    onHourChange = { _ -> },
                    onMinuteChange = { _ -> },
                    onSecondChange = { _ -> },
                    hour = hour,
                    minute = minute,
                    second = second
                )
                MusicList(
                    musicList = musicList
                )
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
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = backToHome) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = com.example.mediation.R.drawable.arrowback_icon),
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

@Preview
@Composable
fun SettingScreenPreview() {
    MediationTheme {
        Surface {
            SettingScreen(backToHome = { })
        }
    }
}