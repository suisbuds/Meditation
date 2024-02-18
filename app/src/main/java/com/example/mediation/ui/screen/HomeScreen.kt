package com.example.mediation.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.mediation.R
import com.example.mediation.data.model.BOTTOM_ICON_LIST
import com.example.mediation.ui.components.MessageCard
import com.example.mediation.ui.components.Timer
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.theme.indicator_color
import com.example.mediation.ui.utils.timeParser
import com.example.mediation.ui.viewmodel.HomeViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToSetting: () -> Unit,
    homeViewModel: HomeViewModel
) {
    val currentTime by homeViewModel.currentTime.collectAsState()
    val endTime by homeViewModel.endTime.collectAsState()
    val isRunning by homeViewModel.isRunning.collectAsState()
    val hasStarted by homeViewModel.hasStarted.collectAsState()
    val enableWriteMessage by homeViewModel.enableWriteMessage.collectAsState()
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = { TopNavigationBar(navigateToSetting, hasStarted) },
            bottomBar = { BottomNavigationBar() },
            containerColor = Color.Transparent
        ) {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_screen),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.matchParentSize()
                )
                AnimatedVisibility(visible = enableWriteMessage) {
                    Popup(
                        alignment = Alignment.Center,
                        properties = PopupProperties(focusable = true),
                        onDismissRequest = { homeViewModel.closeMessageCard() }
                    ) {
                        MessageCard(onClose = { homeViewModel.closeMessageCard() })

                    }
                }
                Timer(
                    isRunning = isRunning,
                    onStart = {
                        homeViewModel.startTimer()
                    },
                    onPause = { homeViewModel.pauseTimer() },
                    currentTime = currentTime.timeParser(),
                    endTime = endTime.timeParser(),
                    hasStarted = hasStarted,
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 144.dp)
                )

            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
) {
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        BOTTOM_ICON_LIST.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.selectedIcon),
                        contentDescription = stringResource(id = item.iconId),
                    )
                },
                label = {
                    Text(text = item.name)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = indicator_color,
                    unselectedIconColor = icon_color,
                    unselectedTextColor = icon_color,
                    selectedIconColor = icon_dark_color,
                    selectedTextColor = icon_dark_color
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navigateToSetting: () -> Unit,
    hasStarted: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent
        ), title = { Text(text = "") },
        actions = {
            val context = LocalContext.current
            IconButton(onClick = {
                if (hasStarted) Toast.makeText(
                    context,
                    "正在倒计时，不可重新设置",
                    Toast.LENGTH_SHORT
                ).show()
                else navigateToSetting()
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.menu_icon),
                    contentDescription = "navigate to setting",
                    tint = icon_dark_color,
                )
            }
        }
    )
}