package com.example.quizier_compose_.ui.signup_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizier_compose_.model.auth.SignUpResponse
import com.example.quizier_compose_.repositories.auth.AuthRepoImpl
import com.example.quizier_compose_.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepoImpl: AuthRepoImpl
) : ViewModel() {
    var signUpResponse = mutableStateOf<Resource<SignUpResponse>>(Resource.Loading())
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")

    fun signUp(email: String, pass: String,)  = viewModelScope.launch {
        isLoading.value = true
        val result = authRepoImpl.signUp(email, pass)
        when (result) {
            is Resource.Success -> {
                isLoading.value = false
                signUpResponse.value = result
            }
            is Resource.Error -> {
                isLoading.value = false
                error.value = result.errorMessage.toString()
            }
            is Resource.Loading -> Unit
        }
    }

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val repeatPassword = mutableStateOf("")

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onPasswordChanged(pass: String) {
        this.password.value = pass
    }

    fun onRepeatPassChanged(pass: String) {
        this.repeatPassword.value = pass
    }
}