package com.example.quizier_compose_.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quizier_compose_.ui.add_questions.AddQuestionsScreen
import com.example.quizier_compose_.ui.add_quiz.AddQuizScreen
import com.example.quizier_compose_.ui.dashboard.DashboardScreen
import com.example.quizier_compose_.ui.profile.ProfileScreen
import com.example.quizier_compose_.util.DashboardNavigation
import com.example.quizier_compose_.util.NavigationItems
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    Scaffold(
        topBar = { AppBar() },
        bottomBar = { BottomBar(navController) },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Navigation(navController = navController)
    }
}

@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationItems.Dashboard.route
    ) {
        composable(NavigationItems.Dashboard.route) {
            DashboardScreen()
        }
        composable(NavigationItems.Profile.route) {
            ProfileScreen()
        }
        composable(NavigationItems.AddQuiz.route) {
            AddQuizScreen(navController)
        }
        composable(DashboardNavigation.AddQuestions.route) {
            AddQuestionsScreen()
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {
    val items = listOf(
        NavigationItems.Dashboard,
        NavigationItems.AddQuiz,
        NavigationItems.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        contentColor = MaterialTheme.colors.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        elevation = 6.dp
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selectedContentColor = MaterialTheme.colors.onSurface,
                unselectedContentColor = Color.White.copy(0.3f),
                alwaysShowLabel = false,
                selected = currentRoute === item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "Quizier") },
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onSurface,
    )
}