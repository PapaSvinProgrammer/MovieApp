package com.example.data.external

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setThemeState(state: Int)
    suspend fun setEntryState(state: Boolean)
    suspend fun setCurrentIcon(index: Int)
    suspend fun setAuthorizationState(state: Boolean)

    fun getThemeState(): Flow<Int>
    fun getEntryState(): Flow<Boolean>
    fun getCurrentIcon(): Flow<Int>
    fun getAuthorizationState(): Flow<Boolean>
}