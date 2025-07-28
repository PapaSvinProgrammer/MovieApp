package com.example.data.internal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.external.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
) : PreferencesRepository {
    override suspend fun setThemeState(state: Int) {
        context.dataStore.edit {
            it[THEME_STATE] = state
        }
    }

    override suspend fun setEntryState(state: Boolean) {
        context.dataStore.edit {
            it[ENTRY_STATE] = state
        }
    }

    override suspend fun setCurrentIcon(index: Int) {
        context.dataStore.edit {
            it[CURRENT_ICON] = index
        }
    }

    override suspend fun setAuthorizationState(state: Boolean) {
        context.dataStore.edit {
            it[AUTH_STATE] = state
        }
    }

    override fun getThemeState(): Flow<Int> {
        return context.dataStore.data.map {
            it[THEME_STATE] ?: 1
        }
    }

    override fun getEntryState(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[ENTRY_STATE] ?: false
        }
    }

    override fun getCurrentIcon(): Flow<Int> {
        return context.dataStore.data.map {
            it[CURRENT_ICON] ?: 1
        }
    }

    override fun getAuthorizationState(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[AUTH_STATE] ?: false
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_DATA_STORE)
        private const val NAME_DATA_STORE = "SettingsMovieApp"
        private val THEME_STATE = intPreferencesKey("theme_state")
        private val ENTRY_STATE = booleanPreferencesKey("entry_state")
        private val CURRENT_ICON = intPreferencesKey("current_icon")
        private val AUTH_STATE = booleanPreferencesKey("auth_state")
    }
}
