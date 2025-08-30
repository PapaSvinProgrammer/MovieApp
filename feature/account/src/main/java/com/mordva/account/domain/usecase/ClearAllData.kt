package com.mordva.account.domain.usecase

import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ClearAllData @Inject constructor(
    private val securityRepository: SecurityRepository
) : UseCase<Unit, Unit>(Dispatchers.IO) {
    override suspend fun run(params: Unit) {
        coroutineScope {
            val securityJob = async { asyncClearSecurityData() }
            val roomJob = async { asyncClearRoomData() }

            awaitAll(securityJob, roomJob)
        }
    }

    private suspend fun asyncClearSecurityData() = coroutineScope {
        securityList.map { type ->
            launch { securityRepository.deleteData(type) }
        }.joinAll()
    }

    private suspend fun asyncClearRoomData() = coroutineScope {

    }

    private companion object {
        val securityList = listOf(
            SecurityType.VK,
            SecurityType.PASSWORD,
            SecurityType.YANDEX
        )
    }
}