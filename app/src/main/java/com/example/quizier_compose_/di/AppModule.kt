package com.example.quizier_compose_.di

import com.example.quizier_compose_.network.auth.AuthService
import com.example.quizier_compose_.repositories.auth.AuthRepo
import com.example.quizier_compose_.repositories.auth.AuthRepoImpl
import com.example.quizier_compose_.repositories.quiz.QuizRepo
import com.example.quizier_compose_.repositories.quiz.QuizRepoImpl
import com.example.quizier_compose_.util.UserPreferences
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepo(
        authService: AuthService,
        userPreferences: UserPreferences
    ): AuthRepo = AuthRepoImpl(authService,userPreferences)

    @Provides
    @Singleton
    fun provideQuizRepo(
        userPreferences: UserPreferences
    ):QuizRepo = QuizRepoImpl(userPreferences)
}