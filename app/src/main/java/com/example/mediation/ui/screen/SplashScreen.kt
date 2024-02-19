package com.example.mediation.ui.screen

import android.content.Intent
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediation.MainActivity
import com.example.mediation.R
import com.example.mediation.ui.theme.MediationTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(1500))
        delay(1000)
        navigateToHome()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .alpha(alpha.value)
        )
    }
}
@Preview
@Composable
fun SplashScreenPreview(){
    Surface {
        MediationTheme {
            SplashScreen {

            }
        }
    }
}