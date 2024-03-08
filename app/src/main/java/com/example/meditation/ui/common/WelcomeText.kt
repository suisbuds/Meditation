package com.example.meditation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_dark_color_brown

//注册和登录页面欢迎文字
@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = text,
            modifier
                .align(Alignment.Start)
                .padding(start = 50.dp),
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 26.sp,
            color = icon_dark_color_brown
        )
    }
}