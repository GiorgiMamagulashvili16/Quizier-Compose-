package com.example.quizier_compose_.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizier_compose_.R
import com.example.quizier_compose_.ui.login_screen.LogInScreen
import com.example.quizier_compose_.ui.signup_screen.SignUpScreen
import com.example.quizier_compose_.ui.splash_screen.SplashScreen
import com.example.quizier_compose_.ui.theme.QuizierComposeTheme
import com.example.quizier_compose_.util.Constants.LOGIN_SCREEN
import com.example.quizier_compose_.util.Constants.SIGNUP_SCREEN
import com.example.quizier_compose_.util.Constants.SPLASH_SCREEN
import dagger.hilt.android.AndroidEntryPoint

typealias drawable = R.drawable
typealias font = R.font

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizierComposeTheme {
                Surface(
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        composable(SPLASH_SCREEN) {
            SplashScreen(navController = navController)
        }
        composable(LOGIN_SCREEN) {
            LogInScreen(navController = navController)
        }
        composable(SIGNUP_SCREEN) {
            SignUpScreen(navController = navController)
        }
    }
}
