package com.example.quizier_compose_.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quizier_compose_.ui.drawable
import com.example.quizier_compose_.util.Constants.ADD_QUIZ_SCREEN
import com.example.quizier_compose_.util.Constants.DASHBOARD_SCREEN
import com.example.quizier_compose_.util.Constants.PROFILE_SCREEN

sealed class NavigationItems(var route: String, var icon: ImageVector, var title: String) {
    object Dashboard : NavigationItems(DASHBOARD_SCREEN, Icons.Default.Dashboard, "Main")
    object AddQuiz : NavigationItems(ADD_QUIZ_SCREEN, Icons.Filled.Add, "Add Quiz")
    object Profile : NavigationItems(PROFILE_SCREEN, Icons.Filled.Person, "Profile")
}
