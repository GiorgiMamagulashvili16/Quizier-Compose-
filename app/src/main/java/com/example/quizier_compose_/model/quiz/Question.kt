package com.example.quizier_compose_.model.quiz

data class Question(
    val quizId: String = "",
    val questionId: String = "",
    val answerOne: String = "",
    val answerTwo: String = "",
    val correctAnswer: String = ""
)
