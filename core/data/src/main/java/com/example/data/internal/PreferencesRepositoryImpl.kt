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

private const val NAME_DATA_STORE = "SettingsMovieApp"
private val DARK_THEME = booleanPreferencesKey("dark_theme")
private val ENTRY_STATE = booleanPreferencesKey("entry_state")
private val CURRENT_ICON = intPreferencesKey("current_icon")

internal class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
) : PreferencesRepository {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_DATA_STORE)
    }

    override suspend fun setDarkTheme(state: Boolean) {
        context.dataStore.edit {
            it[DARK_THEME] = state
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

    override suspend fun getDarkTheme(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[DARK_THEME] ?: true
        }
    }

    override suspend fun getEntryState(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[ENTRY_STATE] ?: false
        }
    }

    override suspend fun getCurrentIcon(): Flow<Int> {
        return context.dataStore.data.map {
            it[CURRENT_ICON] ?: 1
        }
    }
}