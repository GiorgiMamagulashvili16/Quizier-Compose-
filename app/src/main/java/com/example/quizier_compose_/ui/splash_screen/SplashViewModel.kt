package com.example.quizier_compose_.ui.splash_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizier_compose_.util.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {
    var sessionState = mutableStateOf(false)
     fun getLogInSession(){
         viewModelScope.launch {
             val result =userPreferences.getLogInSession()
             sessionState.value = result!!
             Log.d("RESPRESPRESPV", "${userPreferences.getLogInSession()
             }")
         }
    }

}