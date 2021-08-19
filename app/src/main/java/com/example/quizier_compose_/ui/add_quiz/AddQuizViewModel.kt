package com.example.quizier_compose_.ui.add_quiz

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizier_compose_.repositories.quiz.QuizRepoImpl
import com.example.quizier_compose_.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddQuizViewModel @Inject constructor(
    private val quizRepoImpl: QuizRepoImpl
) : ViewModel() {
    var response = mutableStateOf<Resource<Any>>(Resource.Loading())
    var isLoading = mutableStateOf(false)

    fun addQuiz(title: String,imageUri: Uri) = viewModelScope.launch {
        isLoading.value  = true
        withContext(Dispatchers.IO){
            val result = quizRepoImpl.addQuiz(title,imageUri)
            when(result){
                is Resource.Success ->{
                    isLoading.value = false
                    response.value = result
                }
                is Resource.Error ->{
                    isLoading.value = false
                }
            }
        }
    }
    var quizTitle = mutableStateOf("")
    fun onTitleChanged(title: String) {
        this.quizTitle.value = title
    }
}