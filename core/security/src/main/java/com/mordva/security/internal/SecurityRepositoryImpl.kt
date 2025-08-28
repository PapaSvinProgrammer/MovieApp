package com.mordva.security.internal

import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class SecurityRepositoryImpl @Inject constructor(
    val keyStoreManager: KeyStoreManager,
    val dataStoreService: SecurityDataStoreService
) : SecurityRepository {
    override suspend fun setData(
        data: String,
        type: SecurityType
    ): Result<Unit> {
        try {
            val encrypted = keyStoreManager.encrypt(data)
            dataStoreService.saveData(
                token = encrypted,
                type = type
            )
        } catch (e: Exception) {
            return Result.failure(SecurityException(e.message))
        }

        return Result.success(Unit)
    }

    override suspend fun getData(type: SecurityType): Result<String> {
        return try {
            val token = dataStoreService.getData(type).first()
            val decryptedToken = keyStoreManager.decrypt(token)
            Result.success(decryptedToken)
        } catch (e: Exception) {
            Result.failure(SecurityException(e.message))
        }
    }

    override suspend fun tokenIsExist(): SecurityType? {
        return dataStoreService.tokenIsExist()
    }

    override suspend fun dataIsExist(type: SecurityType): Boolean {
        return dataStoreService.dataIsExist(type)
    }

    override suspend fun deleteData(type: SecurityType) {
        dataStoreService.deleteData(type)
    }
}
