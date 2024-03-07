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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.R
import com.example.meditation.ui.common.AskForAgreementChoice
import com.example.meditation.ui.common.CustomButton
import com.example.meditation.ui.common.CustomInputBox
import com.example.meditation.ui.common.WelcomeText
import com.example.meditation.ui.theme.MeditationTheme
import com.example.meditation.ui.theme.icon_color_brown
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "Password"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogInPressed: () -> Unit,
    navigateToSignUp: () -> Unit,
    auth: FirebaseAuth
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
                LoginContent(onLogInPressed = onLogInPressed, navigateToSignUp = navigateToSignUp)
            }
        }
    }
}


@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLogInPressed: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(top = 80.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText(text = "欢迎登录")
        Spacer(modifier = modifier.height(32.dp))
        CustomInputBox(
            imageId = R.drawable.person_icon,
            keyboardType = KeyboardType.Number,
            textHint = "请输入手机号码"
        )
        Spacer(modifier = modifier.height(8.dp))
        CustomInputBox(
            imageId = R.drawable.visibility_icon,
            keyboardType = KeyboardType.Password,
            textHint = "请输入密码"
        )
        Spacer(modifier = modifier.height(16.dp))
        CustomButton(text = "登录", onClick = onLogInPressed)
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = "注册",
            modifier = modifier.clickable { navigateToSignUp() },
            style = TextStyle(fontSize = 12.sp),
            color = icon_color_brown
        )
        Spacer(modifier = modifier.weight(1f))
        AskForAgreementChoice(checked = checked) {
            checked = !checked
        }
    }
}


/*fun createAccount(email: String, password: String,auth: FirebaseAuth) {
    // [START create_user_with_email]
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    MainActivity.appContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    // [END create_user_with_email]
}

private fun signIn(email: String, password: String,auth: FirebaseAuth) {
    // [START sign_in_with_email]
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    MainActivity.appContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    // [END sign_in_with_email]
}

fun updateUI(user: FirebaseUser?){}*/

//@Preview
//@Composable
//fun LoginScreenPreview() {
//    Surface {
//        MeditationTheme {
//            LoginScreen(onLogInPressed = {}, navigateToSignUp = {})
//        }
//    }
//}