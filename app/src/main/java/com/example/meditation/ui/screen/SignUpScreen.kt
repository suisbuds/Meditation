package com.example.meditation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.R
import com.example.meditation.ui.common.CustomInputBox
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.theme.login_screen_color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(modifier: Modifier = Modifier,onSignUp: () -> Unit={}) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(containerColor = Color.Transparent) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.login_screen),
                    contentDescription = "sign up",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize()
                )
                SignUpWrapper(onSignUp=onSignUp)
            }
        }
    }
}


@Composable
fun SignUpWrapper(
    modifier: Modifier = Modifier,
    paddingTop: Dp = 120.dp,
    onSignUp: () -> Unit
) {
    var hasChecked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(top = paddingTop)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "欢迎注册",
            modifier
                .align(Alignment.Start)
                .padding(start = 50.dp),
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 26.sp,
            color = icon_dark_color_brown
        )
        Spacer(modifier = modifier.height(32.dp))
        CustomInputBox(
            imageId = R.drawable.person_icon,
            keyboardType = KeyboardType.Text,
            textTint = "请输入用户名"
        )
        Spacer(modifier = modifier.height(10.dp))
        CustomInputBox(
            imageId = R.drawable.visibility_icon,
            keyboardType = KeyboardType.NumberPassword,
            textTint = "请输入密码"
        )
        Spacer(modifier = modifier.height(32.dp))
        Button(
            onClick = { onSignUp() },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = icon_color_brown)
        ) {
            Text(
                text = "注册",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(15.dp)
                    .background(icon_color_brown)
                    .padding(1.dp)
                    .clip(CircleShape)
                    .background(login_screen_color)
                    .toggleable(value = hasChecked, role = Role.Checkbox, onValueChange = {hasChecked=!hasChecked}),
                contentAlignment = Alignment.Center
            ) {
                if (hasChecked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = icon_color_brown
                    )
                }
            }
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = "点击阅读并同意《用户使用协议》和隐私协议",
                fontSize = 12.sp,
                color = icon_dark_color_brown,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = modifier
            )
        }

    }
}


@Preview
@Composable
fun PreviewSignUpScreen() {
    Surface {
        MeditationTheme {
            SignUpScreen()
        }

    }
}