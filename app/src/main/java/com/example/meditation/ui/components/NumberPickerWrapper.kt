package com.example.meditation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.theme.md_theme_gray
import kotlin.math.abs

@Composable
fun NumberPickerWrapper(
    modifier: Modifier = Modifier,
    selectedValue: Int,
    range: List<Int>,
    onValueChange: (Int) -> Unit,
    title: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        NumberPicker(
            value = selectedValue,
            onValueChange = onValueChange,
            range = range,
            dividersColor = Color.Transparent,
            label = { "${if (abs(it) < 10) "0" else ""}$it" },
            textStyle = TextStyle(
                color = icon_dark_color_brown,
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal
            )
        )
        val textMeasurer = rememberTextMeasurer()
        Canvas(modifier = Modifier.matchParentSize()) {
            drawOverlay(this, textMeasurer, title)
        }
    }
}

private fun DrawScope.drawOverlay(
    canvas: DrawScope,
    textMeasurer: TextMeasurer,
    title: String
) {
    val rectWidth = size.width
    val rectHeight = size.height / 3
    val topRect = Rect(0f, 0f, rectWidth, rectHeight)
    canvas.drawRect(Color.White, topLeft = topRect.topLeft, size = topRect.size)
    val middleRect = Rect(0f, rectHeight, rectWidth, rectHeight * 2)
    val cornerRadius = CornerRadius(4.dp.toPx())
    canvas.drawRoundRect(
        color = md_theme_gray,
        topLeft = middleRect.topLeft,
        size = middleRect.size,
        cornerRadius = cornerRadius,
        style = Stroke(width = 1f)
    )
    val bottomRect = Rect(0f, 2 * rectHeight, rectWidth, rectHeight * 3)
    canvas.drawRect(Color.White, topLeft = bottomRect.topLeft, size = bottomRect.size)
    val textLayoutResult: TextLayoutResult =
        textMeasurer.measure(
            text = title,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                color = icon_dark_color_brown
            )
        )
    val textSize = textLayoutResult.size
    canvas.drawText(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(
            x = size.width / 2 - textSize.width / 2f,
            y = 2 * rectHeight + 5.dp.toPx()
        )
    )
}

@Preview
@Composable
fun NumberPickerPreview() {
    MeditationTheme {
        Surface {
            var state by remember {
                mutableIntStateOf(0)
            }
            NumberPickerWrapper(
                modifier = Modifier.width(80.dp),
                selectedValue = state,
                range = (0..23).toList(),
                onValueChange = { state = it },
                title = "时"
            )
        }
    }
}

