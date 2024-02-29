package com.example.meditation.data.model


sealed class Page {
    data object ScreenDefault : Page()
    data object Slow : Page()
    data object Dream : Page()
    data object Society : Page()
    data object ScreenFlow : Page()
    data object Agreement : Page()
}

val pages = listOf(Page.ScreenDefault, Page.Slow, Page.Dream, Page.Society, Page.ScreenFlow, Page.Agreement)