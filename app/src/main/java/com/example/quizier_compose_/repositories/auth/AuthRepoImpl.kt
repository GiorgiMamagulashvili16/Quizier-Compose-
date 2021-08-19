package com.example.quizier_compose_.repositories.auth

import android.util.Log
import com.example.quizier_compose_.model.auth.LogInResponse
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.model.user.User
import com.example.quizier_compose_.network.auth.AuthService
import com.example.quizier_compose_.util.Resource
import com.example.quizier_compose_.util.UserPreferences
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val userPreferences: UserPreferences
) : AuthRepo {
    private val usersCollection = FirebaseFirestore.getInstance().collection("users")
    override suspend fun logIn(
        email: String,
        password: String,
        rememberMe: Boolean
    ): Resource<LogInResponse> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = authService.logIn(email, password)
                if (response.isSuccessful) {
                    val body = response.body()!!
                    Log.d("RESPRESPRESP", "$rememberMe")
                    userPreferences.saveLogInSession(rememberMe)
                    userPreferences.saveUserLocalId(body.localId!!)
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
                    val user = User(uid = body.localId!!, hasCompletedProfile = false)
                    usersCollection.document(body.localId).set(user).await()
                    Resource.Success(body)
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.toString())
            }
        }
}