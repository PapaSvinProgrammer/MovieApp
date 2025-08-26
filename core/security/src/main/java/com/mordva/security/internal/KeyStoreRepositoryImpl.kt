package com.mordva.security.internal

import com.mordva.security.external.KeyStoreRepository
import javax.inject.Inject

internal class KeyStoreRepositoryImpl @Inject constructor(
    private val fileManager: FileManager,
    private val keyStoreManager: KeyStoreManager
) : KeyStoreRepository {
    override fun encrypt(value: String): Result<String> {
        return try {
            val encrypted = keyStoreManager.encrypt(value)
            fileManager.writeToFile(encrypted, FILE_NAME)
            Result.success(encrypted)
        } catch (e: Throwable) {
            Result.failure(SecurityException(e.message))
        }
    }

    override fun decrypt(): Result<String> {
        return try {
            val value = fileManager.readFromFile(FILE_NAME)

            if (value == null) {
                return Result.failure(SecurityException("No file with name: $FILE_NAME"))
            }

            val decrypted = keyStoreManager.decrypt(value)
            Result.success(decrypted)
        } catch (e: Throwable) {
            Result.failure(SecurityException(e.message))
        }
    }

    override fun delete(): Result<Unit> {
        return try {
            val isDelete = fileManager.deleteFile(FILE_NAME)

            when (isDelete) {
                true -> Result.success(Unit)
                false -> Result.failure(SecurityException("File can not delete"))
            }
        } catch (e: Throwable) {
            Result.failure(SecurityException(e.message))
        }
    }

    private companion object {
        const val FILE_NAME = "secret.txt"
    }
}
