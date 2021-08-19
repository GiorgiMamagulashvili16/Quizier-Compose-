package com.example.quizier_compose_.ui.add_quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddQuizViewModel @Inject constructor() : ViewModel() {

    var quizTitle = mutableStateOf("")
    fun onTitleChanged(title: String) {
        this.quizTitle.value = title
    }
}