package com.example.quizier_compose_.ui.login_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.quizier_compose_.repositories.auth.AuthRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepoImpl: AuthRepoImpl
) : ViewModel() {

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