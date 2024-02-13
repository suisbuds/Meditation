package com.example.mediation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.icon_dark_color

@Composable
fun TimeSetting(
    modifier: Modifier = Modifier,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onSecondChange: (Int) -> Unit,
    hour: Int,
    minute: Int,
    second: Int
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .height(158.dp)
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {
            Spacer(modifier = modifier.height(10.dp))
            TimeSettingHeader(modifier = modifier)
            Surface(color = Color.White) {
                TimePicker(
                    onHourChange = onHourChange,
                    onMinuteChange = onMinuteChange,
                    onSecondChange = onSecondChange,
                    hour = hour,
                    minute = minute,
                    second = second
                )
            }

        }
    }
}

@Composable
fun TimeSettingHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = modifier.width(8.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Canvas(modifier = modifier.matchParentSize()) {
                val rect = Rect(0f, 0f, size.width, size.height)
                drawRect(Color.White, topLeft = rect.topLeft, size = rect.size)
            }
            Text(
                text = "时间",
                color = icon_dark_color,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

val hours = (0..23).toList()
val minutes = (0..59).toList()
val seconds = (0..59).toList()

@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onSecondChange: (Int) -> Unit,
    hour: Int,
    minute: Int,
    second: Int
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            NumberPickerWrapper(
                modifier = Modifier.width(80.dp),
                selectedValue = hour,
                range = hours,
                onValueChange = onHourChange,
                title = "时"
            )
            Spacer(modifier = modifier.width(30.dp))
            NumberPickerWrapper(
                modifier = Modifier.width(80.dp),
                selectedValue = minute,
                range = minutes,
                onValueChange = onMinuteChange,
                title = "分"
            )
            Spacer(modifier = modifier.width(30.dp))
            NumberPickerWrapper(
                modifier = Modifier.width(80.dp),
                selectedValue = second,
                range = seconds,
                onValueChange = onSecondChange,
                title = "秒"
            )
        }
    }


}


@Preview
@Composable
fun TimeSettingPreview() {
    MediationTheme {
        Surface {
            var hourState by remember { mutableIntStateOf(0) }
            var minuteState by remember { mutableIntStateOf(0) }
            var secondState by remember { mutableIntStateOf(0) }
            TimeSetting(
                onHourChange = { hourState = it },
                onMinuteChange = { minuteState = it },
                onSecondChange = { secondState = it },
                hour = hourState,
                minute = minuteState,
                second = secondState
            )
        }
    }
}