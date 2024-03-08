package com.example.meditation.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.meditation.R
import com.example.meditation.data.model.BOTTOM_ICON_LIST
import com.example.meditation.data.model.Page
import com.example.meditation.data.model.pages
import com.example.meditation.ui.components.MessageCard
import com.example.meditation.ui.components.Timer
import com.example.meditation.ui.theme.darkColorList
import com.example.meditation.ui.theme.indicator_color
import com.example.meditation.ui.theme.shallowColorList
import com.example.meditation.ui.utils.timeParser
import com.example.meditation.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToSetting: () -> Unit,
    homeViewModel: HomeViewModel,
    navigateToHistory: () -> Unit
) {
    val currentTime by homeViewModel.currentTime.collectAsState()
    val endTime by homeViewModel.endTime.collectAsState()
    val isRunning by homeViewModel.isRunning.collectAsState()
    val hasStarted by homeViewModel.hasStarted.collectAsState()
    val enableWriteMessage by homeViewModel.enableWriteMessage.collectAsState()

    val themeIndex by homeViewModel.currentThemeIndex.collectAsState()
    val pagerState = rememberPagerState(initialPage = themeIndex) { pages.size }
    val musicTitle by homeViewModel.musicTitle.collectAsState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { pageIndex ->
            homeViewModel.updateThemeIndex(pageIndex)
        }
    }

    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopNavigationBar(
                    navigateToSetting,
                    hasStarted,
                    navigateToHistory = navigateToHistory,
                    onMusicControllerClicked = homeViewModel::onMusicControllerClicked,
                    musicTitle = musicTitle,
                    colorIndex = themeIndex
                )
            },
            bottomBar = { BottomNavigationBar(colorIndex = themeIndex) },
            containerColor = Color.Transparent
        ) {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                HorizontalPager(state = pagerState) { pageIndex ->
                    when (pages[pageIndex]) {
                        Page.ScreenDefault -> Image(
                            painter = painterResource(id = R.drawable.home_screen_default),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                        Page.Slow -> Image(
                            painter = painterResource(id = R.drawable.home_screen_slow),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                        Page.Dream -> Image(
                            painter = painterResource(id = R.drawable.home_screen_dream),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                        Page.Society -> Image(
                            painter = painterResource(id = R.drawable.home_screen_society),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                        Page.ScreenFlow -> Image(
                            painter = painterResource(id = R.drawable.home_screen_flow),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                        Page.Agreement -> Image(
                            painter = painterResource(id = R.drawable.home_screen_agreement),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = modifier.fillMaxSize()
                        )

                    }

                }

                AnimatedVisibility(
                    visible = enableWriteMessage,
                ) {
                    Popup(
                        alignment = Alignment.Center,
                        properties = PopupProperties(focusable = true),
                        onDismissRequest = { homeViewModel.closeMessageCard() }
                    ) {
                        MessageCard(
                            onClose = { homeViewModel.closeMessageCard() },
                            homeViewModel = homeViewModel,
                            colorIndex = themeIndex
                        )
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
                        .padding(vertical = 144.dp),
                    colorIndex = themeIndex
                )

            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    colorIndex: Int
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
                    unselectedIconColor = shallowColorList[colorIndex],
                    unselectedTextColor = shallowColorList[colorIndex],
                    selectedIconColor = darkColorList[colorIndex],
                    selectedTextColor = darkColorList[colorIndex]
                )
            )
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navigateToSetting: () -> Unit,
    hasStarted: Boolean,
    modifier: Modifier = Modifier,
    navigateToHistory: () -> Unit,
    onMusicControllerClicked: () -> Unit,
    musicTitle: String,
    colorIndex: Int
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    //rotate the icon
    var isRotated by remember { mutableStateOf(false) }

    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 180F else 0F,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing), label = ""
    )
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent
        ), title = {
            if (musicTitle == "") {
                Text(text = "暂无可播放音乐", color = darkColorList[colorIndex], fontSize = 12.sp)
            } else {
                Text(
                    text = musicTitle,
                    color = darkColorList[colorIndex],
                    fontSize = 12.sp
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onMusicControllerClicked,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.music_controller),
                    contentDescription = "music controller",
                    modifier = modifier
                        .size(17.dp, 17.dp),
                    colorFilter = ColorFilter.tint(darkColorList[colorIndex])
                )

            }
        },
        actions = {
            val context = LocalContext.current
            IconButton(
                onClick = {
                    expanded = !expanded
                    isRotated = !isRotated
                }, modifier = modifier.rotate(rotationAngle)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.menu_icon),
                    contentDescription = "navigate to setting",
                    tint = darkColorList[colorIndex],
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    leadingIcon = {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.setting_icon),
                            contentDescription = "setting",
                            colorFilter = ColorFilter.tint(darkColorList[colorIndex])
                        )
                    },
                    onClick = {
                        if (hasStarted) {
                            Toast.makeText(
                                context,
                                "正在倒计时，不可重新设置",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            navigateToSetting()
                        }
                        expanded = false
                    },
                    text = {
                        Text(
                            text = "设置", color = shallowColorList[colorIndex],
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    },
                )
                Divider()
                DropdownMenuItem(
                    leadingIcon = {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.history_icon),
                            contentDescription = "history",
                            colorFilter = ColorFilter.tint(darkColorList[colorIndex])
                        )
                    },
                    onClick = {
                        navigateToHistory()
                        expanded = false
                    },
                    text = {
                        Text(
                            text = "历史留言", color = shallowColorList[colorIndex],
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    },
                )
            }
        }
    )
}

