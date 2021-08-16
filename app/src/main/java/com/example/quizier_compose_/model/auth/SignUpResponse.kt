package com.example.quizier_compose_.model.auth

data class SignUpResponse(
    val idToken: String?,
    val email: String?,
    val refreshToken: String?,
    val expiresIn: String?,
    val localId: String?,
)
