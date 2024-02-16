package com.example.mediation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.R
import com.example.mediation.data.model.BOTTOM_ICON_LIST
import com.example.mediation.ui.components.MessageCard
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color
import com.example.mediation.ui.theme.indicator_color
import com.example.mediation.ui.viewmodel.HomeViewModel

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

    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = { TopNavigationBar(navigateToSetting) },
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
                Box(
                    modifier = modifier
                        .padding(
                            horizontal = 36.dp, vertical = 144.dp
                        )
                        .padding(bottom = 48.dp)
                        .align(Alignment.Center)
                        .background(Color.Transparent)
                ) {
                    MessageCard()
                }
/*
                Box(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 144.dp)
                ) {
                    Timer(
                        isRunning = isRunning,
                        onStart = { homeViewModel.startTimer() },
                        onPause = { homeViewModel.pauseTimer() },
                        currentTime = currentTime.timeParser(),
                        endTime = endTime.timeParser(),
                        hasStarted = hasStarted
                    )
                }
*/
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
    NavigationBar(modifier = modifier.fillMaxWidth(), containerColor = Color.Transparent, tonalElevation = 0.dp) {
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
fun TopNavigationBar(navigateToSetting: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent
        ), title = { Text(text = "") },
        actions = {
            IconButton(onClick = navigateToSetting) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.menu_icon),
                    contentDescription = "navigate to setting",
                    tint = icon_dark_color,
                )
            }
        }
    )
}