package com.example.quizier_compose_.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext val appCtx:Context
) {

    companion object {
        private const val CURRENT_USER_ID_KEY = "current_user_id_key"
        private const val AUTH_SESSION_KEY = "remember_me"
        private const val USER_PREFERENCE_NAME = "user_preference"
    }

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCE_NAME
    )

    suspend fun saveUserLocalId(value: String) {
        val dataStoreKey = stringPreferencesKey(CURRENT_USER_ID_KEY)
        appCtx.dataStore.edit { uid ->
            uid[dataStoreKey] = value
        }
    }

    suspend fun getLocalId(): String? {
        val key = stringPreferencesKey(CURRENT_USER_ID_KEY)
        return appCtx.dataStore.data.first()[key]
    }

    suspend fun saveLogInSession(value: Boolean) {
        val key = booleanPreferencesKey(AUTH_SESSION_KEY)
        appCtx.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun getLogInSession(): Boolean? {
        val key = booleanPreferencesKey(AUTH_SESSION_KEY)
        return appCtx.dataStore.data.first()[key]
    }
}