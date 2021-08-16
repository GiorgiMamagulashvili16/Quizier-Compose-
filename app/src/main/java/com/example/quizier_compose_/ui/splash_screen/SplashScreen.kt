package com.example.quizier_compose_.ui.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizier_compose_.ui.drawable
import com.example.quizier_compose_.util.Constants.LOGIN_SCREEN
import com.example.quizier_compose_.util.Constants.SPLASH_SCREEN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scale = remember {
        Animatable(0f)
    }
    val scaleForText = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scaleForText.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(LOGIN_SCREEN){
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }

    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Image(
                painter = painterResource(id = drawable.ic_puzzle_piece),
                contentDescription = "icon",
                modifier = Modifier.scale(scale.value)
            )
            Text(
                text = "Quizier", modifier = Modifier
                    .align(CenterHorizontally)
                    .scale(scaleForText.value),
                fontSize = 31.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}