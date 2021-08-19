package com.example.quizier_compose_.repositories.quiz

import android.net.Uri
import com.example.quizier_compose_.model.quiz.Quiz
import com.example.quizier_compose_.util.Resource

interface QuizRepo {
    suspend fun addQuiz(title:String,image:Uri):Resource<Any>
}