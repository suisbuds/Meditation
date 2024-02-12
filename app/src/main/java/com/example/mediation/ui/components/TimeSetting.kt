package com.example.mediation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediation.ui.theme.MediationTheme
import com.example.mediation.ui.theme.icon_dark_color

@Composable
fun TimeSetting(
    modifier: Modifier = Modifier,
    onHourChange: (String) -> Unit,
    onMinuteChange: (String) -> Unit,
    onSecondChange: (String) -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier
            .height(250.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {
            TimeSettingHeader(modifier = modifier)
            Spacer(modifier = modifier.height(25.dp))
            TimeSettingField(
                onHourChange = onHourChange,
                onMinuteChange = onMinuteChange,
                onSecondChange = onSecondChange
            )
        }
    }
}

@Composable
fun TimeSettingHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "时间",
            color = icon_dark_color,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TimeSettingField(
    modifier: Modifier = Modifier,
    onHourChange: (String) -> Unit,
    onMinuteChange: (String) -> Unit,
    onSecondChange: (String) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = modifier.width(30.dp))
        TimeSettingCard(
            title = "时",
            currentNumber = "00",
            onValueChange = onHourChange
        )
        Spacer(modifier = modifier.width(20.dp))
        TimeSettingCard(
            title = "分钟",
            currentNumber = "00",
            onValueChange = onMinuteChange
        )
        Spacer(modifier = modifier.width(20.dp))
        TimeSettingCard(
            title = "秒",
            currentNumber = "00",
            onValueChange = onSecondChange
        )
    }
}

@Composable
fun TimeSettingCard(
    modifier: Modifier = Modifier,
    title: String,
    currentNumber: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .height(85.dp)
            .width(90.dp)
    ) {
        OutlinedTextField(
            value = currentNumber,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = icon_dark_color,
                textAlign = TextAlign.Center,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
                .height(55.dp)
                .width(160.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = icon_dark_color,
                unfocusedBorderColor = icon_dark_color
            )
        )
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = title,
            color = icon_dark_color,
            modifier = modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W300,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    }
}

@Preview
@Composable
fun TimeSettingPreview() {
    MediationTheme {
        Surface {
            TimeSetting(
                onHourChange = { _ -> },
                onMinuteChange = { _ -> },
                onSecondChange = { _ -> })
        }
    }
}