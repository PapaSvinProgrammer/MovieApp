package com.mordva.security.internal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.mordva.security.internal.util.SecurityType
import com.mordva.security.internal.util.toPreferencesKey
import com.mordva.util.ApplicationScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ApplicationScope
internal class SecurityDataStoreService @Inject constructor(
    private val context: Context
) {
    suspend fun saveData(token: String, type: SecurityType) {
        context.dataStore.edit {
            it[type.toPreferencesKey()] = token
        }
    }

    fun getData(type: SecurityType): Flow<String> {
        return context.dataStore.data.map {
            it[type.toPreferencesKey()] ?: ""
        }
    }

    suspend fun deleteData(type: SecurityType) {
        context.dataStore.edit { preferences ->
            preferences.remove(type.toPreferencesKey())
        }
    }

    suspend fun tokenIsExist(): SecurityType? {
        if (dataIsExist(SecurityType.YANDEX)) {
            return SecurityType.YANDEX
        }

        if (dataIsExist(SecurityType.VK)) {
            return SecurityType.VK
        }

        return null
    }

    suspend fun dataIsExist(type: SecurityType): Boolean {
        return context.dataStore.data.map { preferences ->
            val token = preferences[type.toPreferencesKey()]
            !token.isNullOrBlank()
        }.first()
    }

    private companion object Companion {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
        const val DATA_STORE_NAME = "security_data_store"
    }
}
