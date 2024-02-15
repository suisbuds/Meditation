package com.example.mediation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .height(140.dp)
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
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
            val textMeasurer = rememberTextMeasurer()
            Canvas(modifier = Modifier.matchParentSize()) {
                drawTimeSettingHeader(this, textMeasurer)
            }
        }
    }
}

private fun drawTimeSettingHeader(
    canvas: DrawScope,
    textMeasurer: TextMeasurer
) {
    val textLayoutResult: TextLayoutResult =
        textMeasurer.measure(
            text = AnnotatedString("时间"),
            style = TextStyle(
                color = icon_dark_color,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    canvas.drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(x = 40f, y = 45f)
    )
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
            Spacer(modifier = modifier.width(20.dp))
            NumberPickerWrapper(
                modifier = Modifier.width(80.dp),
                selectedValue = minute,
                range = minutes,
                onValueChange = onMinuteChange,
                title = "分钟"
            )
            Spacer(modifier = modifier.width(20.dp))
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