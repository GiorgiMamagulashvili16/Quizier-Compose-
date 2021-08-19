package com.example.quizier_compose_.repositories.auth

import com.example.quizier_compose_.model.auth.LogInResponse
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.util.Resource

interface AuthRepo {

    suspend fun logIn(email:String,password:String,rememberMe:Boolean):Resource<LogInResponse>
    suspend fun signUp(email: String,password: String):Resource<SignUpResponse>
}