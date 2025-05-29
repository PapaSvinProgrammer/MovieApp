package com.example.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setDarkTheme(state: Boolean)
    suspend fun setEntryState(state: Boolean)

    suspend fun getDarkTheme(): Flow<Boolean>
    suspend fun getEntryState(): Flow<Boolean>
}