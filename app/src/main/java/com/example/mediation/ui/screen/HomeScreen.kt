package com.example.mediation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.R
import com.example.mediation.data.model.BOTTOM_ICON_LIST

@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Image(
                painter = painterResource(id = R.drawable.home_screen),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}


@Composable
fun BottomNavigationBar(
) {
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent, tonalElevation = 0.dp) {
        BOTTOM_ICON_LIST.forEachIndexed() { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(imageVector = item.selectedIcon, contentDescription = stringResource(id = item.iconId)) },
                label = { Text(text = item.name) })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent
        ), title = { Text(text = "") },
        actions = {
            IconButton(onClick = { /* navigate to setting */ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "navigate to setting")
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    )
}