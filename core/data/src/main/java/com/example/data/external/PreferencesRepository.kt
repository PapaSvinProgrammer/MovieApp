package com.example.data.external

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setThemeState(state: Int)
    suspend fun setEntryState(state: Boolean)
    suspend fun setCurrentIcon(index: Int)

    suspend fun getThemeState(): Flow<Int>
    suspend fun getEntryState(): Flow<Boolean>
    suspend fun getCurrentIcon(): Flow<Int>
}