package com.example.meditation.data.model

import com.example.meditation.R

object TestTheme {
    val backgroundTheme =
        listOf(
            BackgroundTheme(imageID = R.drawable.home_screen_1),
            BackgroundTheme(imageID = R.drawable.home_screen_2),
            BackgroundTheme(imageID = R.drawable.home_screen_3)
        )
}

sealed class Page {
    data object ThemeFirst : Page()
    data object ThemeSecond : Page()
    data object ThemeThird : Page()
}

val pages = listOf(Page.ThemeFirst, Page.ThemeSecond, Page.ThemeThird)