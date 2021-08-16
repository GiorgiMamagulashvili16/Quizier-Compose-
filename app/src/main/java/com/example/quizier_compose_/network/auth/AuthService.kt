package com.example.quizier_compose_.network.auth

import com.example.quizier_compose_.model.auth.LogInResponse
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.util.Constants.FIREBASE_KEY
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("/v1/accounts:signInWithPassword?key=$FIREBASE_KEY")
    suspend fun logIn(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("returnSecureToken") returnSecureToken: Boolean = true
    ): Response<LogInResponse>

    @FormUrlEncoded
    @POST("/v1/accounts:signUp?key=$FIREBASE_KEY")
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("returnSecureToken") returnSecureToken: Boolean = true
    ): Response<SignUpResponse>
}