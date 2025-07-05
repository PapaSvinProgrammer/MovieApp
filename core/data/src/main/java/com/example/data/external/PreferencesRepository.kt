package com.example.data.external

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setDarkTheme(state: Boolean)
    suspend fun setEntryState(state: Boolean)
    suspend fun setCurrentIcon(index: Int)

    suspend fun getDarkTheme(): Flow<Boolean>
    suspend fun getEntryState(): Flow<Boolean>
    suspend fun getCurrentIcon(): Flow<Int>
}