package com.example.quizier_compose_.repositories.quiz

import android.net.Uri
import com.example.quizier_compose_.model.quiz.Quiz
import com.example.quizier_compose_.util.Resource
import com.example.quizier_compose_.util.UserPreferences
import com.example.quizier_compose_.util.safeCall
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class QuizRepoImpl @Inject constructor(
    private val userPreferences: UserPreferences,
) :QuizRepo{
    private val storage = Firebase.storage
    private val quizCollection = FirebaseFirestore.getInstance().collection("quiz")
    override suspend fun addQuiz(title:String,image:Uri): Resource<Any> {
        return safeCall {
            val authorId = userPreferences.getLocalId()
            val quizId = UUID.randomUUID().toString()
            val imageUpl = storage.getReference(quizId).putFile(image).await()
            val imageUrl = imageUpl.metadata?.reference?.downloadUrl?.await().toString()
            val quiz = authorId?.let { Quiz(quizId,it,imageUrl) }
            quiz?.let {
                quizCollection.document(quizId).set(it).await()
            }
            Resource.Success(Any())
        }
    }
}