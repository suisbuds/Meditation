package com.example.meditation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
//用户协议
@Composable
fun AskForAgreementChoice(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked,
            onClick = onClick,
            modifier = modifier.padding(start = 50.dp),
            colors = RadioButtonDefaults.colors(
                selectedColor = icon_color_brown,
                unselectedColor = icon_color_brown
            )
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "点击阅读并同意《用户使用协议》和隐私协议",
            fontSize = 12.sp,
            color = icon_dark_color_brown,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Medium,
            modifier = modifier.weight(1f)
        )
    }
}
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
//最下面的按钮
@Composable
fun CustomButton(modifier: Modifier = Modifier, text: String,onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = icon_dark_color_brown)
        ) {
            Text(text = text, color = Color.White)
        }
    }

}