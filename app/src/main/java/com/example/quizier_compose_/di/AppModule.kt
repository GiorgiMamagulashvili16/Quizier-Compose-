package com.example.quizier_compose_.di

import com.example.quizier_compose_.network.auth.AuthService
import com.example.quizier_compose_.repositories.auth.AuthRepo
import com.example.quizier_compose_.repositories.auth.AuthRepoImpl
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
        firestore: FirebaseFirestore
    ): AuthRepo = AuthRepoImpl(authService, firestore)
}