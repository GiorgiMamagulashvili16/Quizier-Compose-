package com.example.quizier_compose_.ui.login_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizier_compose_.model.auth.LogInResponse
import com.example.quizier_compose_.repositories.auth.AuthRepoImpl
import com.example.quizier_compose_.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepoImpl: AuthRepoImpl
) : ViewModel() {

    var logInResponse = mutableStateOf<Resource<LogInResponse>>(Resource.Loading())
    var errorMes = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun logIn(email: String, password: String, rememberMe: Boolean) = viewModelScope.launch {
        isLoading.value = true
        withContext(Dispatchers.IO) {
            val result = authRepoImpl.logIn(email, password, rememberMe)
            when (result) {
                is Resource.Success -> {
                    isLoading.value = false
                    logInResponse.value = result
                }
                is Resource.Error -> {
                    isLoading.value = false
                    errorMes.value = result.errorMessage.toString()
                }
            }
        }
    }


    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val isChecked = mutableStateOf(false)

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
    }

    fun onCheckBoxChanged(isChecked: Boolean) {
        this.isChecked.value = isChecked
    }
}