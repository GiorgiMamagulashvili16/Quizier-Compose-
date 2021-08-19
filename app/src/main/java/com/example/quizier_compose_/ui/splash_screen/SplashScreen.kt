package com.example.quizier_compose_.ui.splash_screen

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizier_compose_.ui.drawable
import com.example.quizier_compose_.util.Constants.LOGIN_SCREEN
import com.example.quizier_compose_.util.Constants.MAIN_SCREEN
import com.example.quizier_compose_.util.Constants.SPLASH_SCREEN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    vm: SplashViewModel = hiltViewModel()
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
    vm.getLogInSession()
    val state = vm.sessionState
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
        Log.d("RESPRESPRESPS", "${state.value}")
        if (state.value) {
            navController.navigate(MAIN_SCREEN) {
                popUpTo(SPLASH_SCREEN) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(LOGIN_SCREEN) {
                popUpTo(SPLASH_SCREEN) {
                    inclusive = true
                }
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
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 39.sp
                        )
                    ) {
                        append("Q")
                    }
                    append("uizier")
                },
                modifier = Modifier
                    .align(CenterHorizontally)
                    .scale(scaleForText.value),
                fontSize = 31.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}