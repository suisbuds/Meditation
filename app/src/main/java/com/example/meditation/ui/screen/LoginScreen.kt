package com.example.meditation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.R
import com.example.meditation.ui.common.AgreementCheck
import com.example.meditation.ui.common.JumpButton
import com.example.meditation.ui.common.CustomInputBox
import com.example.meditation.ui.common.WelcomeText
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(containerColor = Color.Transparent) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.login_screen),
                    contentDescription = "log in",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize()
                )
                LoginWrapper(onLogin = onLogin, navigateToSignUp = navigateToSignUp)
            }
        }
    }
}


@Composable
fun LoginWrapper(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(top = 120.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText(text = "欢迎登录")
        Spacer(modifier = modifier.height(32.dp))
        CustomInputBox(
            imageId = R.drawable.person_icon,
            keyboardType = KeyboardType.Text,
            textHint = "请输入用户名"
        )
        Spacer(modifier = modifier.height(10.dp))
        CustomInputBox(
            imageId = R.drawable.visibility_icon,
            keyboardType = KeyboardType.NumberPassword,
            textHint = "请输入密码"
        )
        Spacer(modifier = modifier.height(32.dp))
        JumpButton(text = "登录", onClick = { onLogin() })
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "注册",
            modifier = modifier.clickable { navigateToSignUp() },
            style = TextStyle(
                fontSize = 14.sp, fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium
            ),
            color = icon_dark_color_brown,
        )
        Spacer(modifier = modifier.weight(1f))
        AgreementCheck(checked = checked) {
            checked = !checked
        }
    }
}



