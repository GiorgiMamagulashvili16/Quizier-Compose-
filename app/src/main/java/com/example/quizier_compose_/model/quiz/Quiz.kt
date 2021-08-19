package com.example.quizier_compose_.model.quiz

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Quiz(
    val quizId: String = "",
    val authorId: String = "",
    val quizImage: String = "",
    @get:Exclude var authorUserName: String = "",
    @get:Exclude var authorImage: String = ""
)
