package com.mordva.security.external

import com.mordva.security.internal.util.SecurityType

interface SecurityRepository {
    suspend fun setData(data: String, type: SecurityType): Result<Unit>
    suspend fun getData(type: SecurityType): Result<String>

    suspend fun tokenIsExist(): SecurityType?
    suspend fun dataIsExist(type: SecurityType): Boolean

    suspend fun deleteData(type: SecurityType)
}