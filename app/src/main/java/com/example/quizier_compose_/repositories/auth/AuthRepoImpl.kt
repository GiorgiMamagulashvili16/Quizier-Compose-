package com.example.quizier_compose_.repositories.auth

import com.example.quizier_compose_.model.auth.LogInResponse
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.network.auth.AuthService
import com.example.quizier_compose_.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val fireStore: FirebaseFirestore
) : AuthRepo {
    private val users = fireStore.collection("users")
    override suspend fun logIn(email: String, password: String): Resource<LogInResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = authService.logIn(email, password)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    Resource.Success(body)
                } else {
                    Resource.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                Resource.Error(e.toString())
            }
        }

    override suspend fun signUp(email: String, password: String): Resource<SignUpResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = authService.signUp(email, password)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    Resource.Success(body)
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.toString())
            }
        }
}