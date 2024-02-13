package com.example.mediation.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.mediation.R
import com.example.mediation.ui.theme.icon_color
import com.example.mediation.ui.theme.icon_dark_color


//怎样使文字初始为开始
@Composable
fun Timer(
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    currentTime: String,
    endTime: String,
    hasStarted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = {
                if (isRunning) {
                    onPause()
                } else {
                    onStart()
                }
            },
            containerColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                focusedElevation = 0.dp,
                hoveredElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            if (isRunning) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.pause_icon),
                    contentDescription = "pause timer",
                    tint = icon_dark_color
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.play_icon),
                    contentDescription = "start timer",
                    tint = icon_dark_color
                )
            }
        }
        AnimatedContent(
            targetState = hasStarted, transitionSpec = {
                fadeIn(
                    animationSpec = tween(0)
                ) togetherWith fadeOut(animationSpec = tween(0))
            },
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {},
            label = "Animated start timer"
        ) { targetState ->
            when (targetState) {
                true -> {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = icon_dark_color)) {
                                append(currentTime)
                            }
                            withStyle(style = SpanStyle(color = icon_color)) {
                                append(" / $endTime")
                            }
                        },
                        fontWeight = FontWeight.SemiBold
                    )
                }
                false -> {
                    Text(text = "开始", color = icon_dark_color, fontWeight = FontWeight.SemiBold)
                }
            }

        }
    }
}

