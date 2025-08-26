package com.mordva.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setThemeState(state: Int)
    suspend fun setEntryState(state: Boolean)
    suspend fun setAuthorizationState(state: Boolean)

    fun getThemeState(): Flow<Int>
    fun getEntryState(): Flow<Boolean>
    fun getAuthorizationState(): Flow<Boolean>
}