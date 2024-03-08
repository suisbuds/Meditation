package com.example.meditation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown

//最下面的按钮
@Composable
fun JumpButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = { onClick() },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = icon_color_brown)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}