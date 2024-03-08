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
import com.example.meditation.ui.common.AgreementCheck
import com.example.meditation.ui.common.CustomInputBox
import com.example.meditation.ui.common.JumpButton
import com.example.meditation.ui.common.WelcomeText
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.NunitoFontFamily
import com.example.meditation.ui.theme.icon_color_brown
import com.example.meditation.ui.theme.icon_dark_color_brown
import com.example.meditation.ui.theme.login_screen_color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUp: () -> Unit = {},
    currentUsername: String,
    currentPassword: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Scaffold(containerColor = Color.Transparent) {
            Box(modifier = modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.login_screen),
                    contentDescription = "sign up",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.fillMaxSize()
                )
                SignUpWrapper(
                    onSignUp = onSignUp,
                    currentUsername = currentUsername,
                    currentPassword = currentPassword,
                    onUsernameChange = onUsernameChange,
                    onPasswordChange = onPasswordChange
                )
            }
        }
    }
}


@Composable
fun SignUpWrapper(
    modifier: Modifier = Modifier,
    paddingTop: Dp = 120.dp,
    onSignUp: () -> Unit,
    currentUsername: String,
    currentPassword: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
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
            keyboardType = KeyboardType.Text,
            textHint = "请输入用户名",
            onValueChange = onUsernameChange,
            text = currentUsername
        )
        Spacer(modifier = modifier.height(10.dp))
        CustomInputBox(
            imageId = R.drawable.visibility_icon,
            keyboardType = KeyboardType.NumberPassword,
            textHint = "请输入密码",
            onValueChange = onPasswordChange,
            text = currentPassword
        )
        Spacer(modifier = modifier.height(32.dp))
        JumpButton(text = "注册", onClick = {onSignUp()})
        Spacer(modifier = modifier.weight(1f))
        AgreementCheck(checked = checked) {
            checked = !checked
        }

    }
}


@Preview
@Composable
fun PreviewSignUpScreen() {
    Surface {
        MeditationTheme {
            SignUpScreen(
                currentUsername = "",
                currentPassword = "",
                onUsernameChange = { },
                onPasswordChange = { })
        }

    }
}
