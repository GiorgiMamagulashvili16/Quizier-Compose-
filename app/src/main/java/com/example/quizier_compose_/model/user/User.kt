package com.example.quizier_compose_.model.user

data class User(
    val userName: String = "",
    val uid: String = "",
    val profileImgUrl: String = "",
    val hasCompletedProfile: Boolean = false
)
