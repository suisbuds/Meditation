package com.example.meditation.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import com.example.meditation.R
import com.example.meditation.ui.theme.darkColorList
import com.example.meditation.ui.theme.shallowColorList


//怎样使文字初始为开始
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Timer(
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    currentTime: String,
    endTime: String,
    hasStarted: Boolean,
    modifier: Modifier = Modifier,
    colorIndex: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
                    tint = darkColorList[colorIndex]
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.play_icon),
                    contentDescription = "start timer",
                    tint = darkColorList[colorIndex]
                )
            }
        }
        AnimatedContent(
            targetState = hasStarted, transitionSpec = {
                slideInVertically(
                    animationSpec = tween(100), initialOffsetY = { fullHeight -> fullHeight }
                ) + fadeIn(animationSpec = tween(100)) with slideOutVertically(
                    animationSpec = tween(100),
                    targetOffsetY = { fullHeight -> fullHeight }) + fadeOut(
                    animationSpec =
                    tween(100)
                )
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
                            withStyle(style = SpanStyle(color = darkColorList[colorIndex])) {
                                append(currentTime)
                            }
                            withStyle(style = SpanStyle(color = shallowColorList[colorIndex])) {
                                append(" / $endTime")
                            }
                        },
                        fontWeight = FontWeight.SemiBold
                    )
                }

                false -> {
                    Text(text = "开始", color = darkColorList[colorIndex], fontWeight = FontWeight.SemiBold)
                }
            }

        }
    }
}

