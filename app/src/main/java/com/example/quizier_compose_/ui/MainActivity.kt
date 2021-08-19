package com.example.quizier_compose_.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.example.quizier_compose_.R
import com.example.quizier_compose_.ui.main.MainScreen
import com.example.quizier_compose_.ui.login_screen.LogInScreen
import com.example.quizier_compose_.ui.signup_screen.SignUpScreen
import com.example.quizier_compose_.ui.splash_screen.SplashScreen
import com.example.quizier_compose_.ui.theme.QuizierComposeTheme
import com.example.quizier_compose_.util.Constants.LOGIN_SCREEN
import com.example.quizier_compose_.util.Constants.MAIN_SCREEN
import com.example.quizier_compose_.util.Constants.SIGNUP_SCREEN
import com.example.quizier_compose_.util.Constants.SPLASH_SCREEN
import com.example.quizier_compose_.util.UserPreferences
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

typealias drawable = R.drawable
typealias font = R.font

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizierComposeTheme {
                Surface(
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberAnimatedNavController()
                    Navigation(navController = navController, userPreferences)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
    userPreferences: UserPreferences
) {

    AnimatedNavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN,
            exitTransition = { _, _ ->
                fadeOut(animationSpec = tween(800))
            }
        ) {
            SplashScreen(navController = navController)
        }
        composable(LOGIN_SCREEN,
            enterTransition = { _, _ ->
                fadeIn(animationSpec = tween(800))
            },
            exitTransition = { _, _ ->
                fadeOut(animationSpec = tween(800))
            }
        ) {
            LogInScreen(navController = navController, userPreferences)
        }
        composable(SIGNUP_SCREEN,
            enterTransition = { _, _ ->
                fadeIn(animationSpec = tween(800))
            },
            exitTransition = { _, _ ->
                fadeOut(animationSpec = tween(800))
            }
        ) {
            SignUpScreen(navController = navController)
        }
        composable(MAIN_SCREEN,
            enterTransition = { _, _ ->
                fadeIn(animationSpec = tween(800))
            },
            exitTransition = { _, _ ->
                fadeOut(animationSpec = tween(800))
            }
        ) {
            MainScreen()
        }
    }
}
