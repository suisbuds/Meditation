package com.example.mediation.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mediation.R


object Name {
    const val HOME = "首页"
    const val FRIENDS = "禅友圈"
    const val ME = "我的"
}

data class BottomIcon(
    val name: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconId: Int
)


val BOTTOM_ICON_LIST = listOf(
    BottomIcon(name = Name.HOME, selectedIcon = Icons.Default.Home, unselectedIcon = Icons.Default.Home, R.string.home),
    BottomIcon(
        name = Name.FRIENDS,
        selectedIcon = Icons.Default.Face,
        unselectedIcon = Icons.Default.Face,
        R.string.friends
    ),
    BottomIcon(name = Name.ME, selectedIcon = Icons.Default.Person, unselectedIcon = Icons.Default.Person, R.string.me),
)