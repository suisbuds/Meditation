package com.example.mediation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = { TopNavigationBar() },
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
        BOTTOM_ICON_LIST.forEachIndexed() { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(imageVector = item.selectedIcon, contentDescription = stringResource(id = item.iconId)) },
                label = { Text(text = item.name) },
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Transparent
        ), title = { Text(text = "") },
        actions = {
            IconButton(onClick = { /* navigate to setting */ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "navigate to setting")
            }
        }
    )
}