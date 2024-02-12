package com.example.mediation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.mediation.R
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color


//怎样使文字初始为开始
@Composable
fun TimeControl(
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    currentTime: String,
    endTime: String,
    hasStarted: Boolean
) {
    Column {
        FloatingActionButton(onClick = {
            if (isRunning) {
                onPause()
            } else {
                onStart()
            }
        }, containerColor = Color.Transparent) {
            if (isRunning) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.pause_icon),
                    contentDescription = "pause timer",
                    tint = icon_dark_color
                )
            } else {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "start timer", tint = icon_dark_color)
            }
        }
        if (hasStarted) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = icon_dark_color)) {
                        append(currentTime)
                    }
                    withStyle(style = SpanStyle(color = icon_color)) {
                        append("/$endTime")
                    }
                })
        } else {
            Text(text = "开始", color = icon_dark_color)
        }
    }

}

