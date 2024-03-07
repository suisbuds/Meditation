package com.example.meditation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.meditation.R
import com.example.meditation.ui.common.AskForAgreementChoice
import com.example.meditation.ui.common.CustomButton
import com.example.meditation.ui.common.CustomInputBox
import com.example.meditation.ui.common.WelcomeText
import com.example.meditation.ui.theme.MeditationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, onSignUpPressed: () -> Unit) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(containerColor = Color.Transparent) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.login_screen),
                    contentDescription = "sign up",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize()
                )
                SignUpWrapper(onSignUpPressed = onSignUpPressed)
            }
        }
    }
}


@Composable
fun SignUpWrapper(
    modifier: Modifier = Modifier,
    paddingTop: Dp = 80.dp,
    onSignUpPressed: () -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(top = paddingTop)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WelcomeText(text = "欢迎注册")
        Spacer(modifier = modifier.height(32.dp))
        CustomInputBox(
            imageId = R.drawable.person_icon,
            keyboardType = KeyboardType.Number,
            textHint = "请输入手机号码"
        )
        Spacer(modifier = modifier.height(8.dp))
        CustomInputBox(
            imageId = R.drawable.lock_icon,
            keyboardType = KeyboardType.Number,
            textHint = "请输入验证码"
        )
        Spacer(modifier = modifier.height(8.dp))
        CustomInputBox(
            imageId = R.drawable.visibility_icon,
            keyboardType = KeyboardType.Password,
            textHint = "请输入密码"
        )
        Spacer(modifier = modifier.height(16.dp))
        CustomButton(text = "注册", onClick = onSignUpPressed)
        Spacer(modifier = modifier.weight(1f))
        AskForAgreementChoice(checked = checked) {
            checked = !checked
        }

    }
}


@Preview
@Composable
fun PreviewSignUpScreen() {
    Surface {
        MeditationTheme {
            SignUpScreen(onSignUpPressed = {})
        }
    }
}