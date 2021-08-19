package com.example.quizier_compose_.util

sealed class DashboardNavigation(var route:String) {
    object AddQuestions:DashboardNavigation("add_questions")

}