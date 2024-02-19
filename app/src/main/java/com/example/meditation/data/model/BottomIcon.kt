package com.example.meditation.data.model

import com.example.mediation.R


object Name {
    const val HOME = "首页"
    const val FRIENDS = "禅友圈"
    const val ME = "我的"
}

data class BottomIcon(
    val name: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconId: Int
)


val BOTTOM_ICON_LIST = listOf(
    BottomIcon(
        name = Name.HOME,
        selectedIcon = R.drawable.home_icon,
        unselectedIcon = R.drawable.home_icon,
        R.string.home
    ),
    BottomIcon(
        name = Name.FRIENDS,
        selectedIcon = R.drawable.chat_icon,
        unselectedIcon = R.drawable.chat_icon,
        R.string.friends
    ),
    BottomIcon(name = Name.ME, selectedIcon = R.drawable.me_icon, unselectedIcon = R.drawable.me_icon, R.string.me),
)

