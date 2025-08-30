package com.mordva.account.domain.usecase

import com.mordva.account.domain.model.UserAccount
import com.mordva.account.domain.repository.UserAccountRepository
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.util.UseCase
import com.mordva.util.error.ClientException
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetUserAccount @Inject constructor(
    private val userAccountRepository: UserAccountRepository,
    private val securityRepository: SecurityRepository
) : UseCase<Unit, Result<UserAccount>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): Result<UserAccount> {
        val type = securityRepository.tokenIsExist()

        return when (type) {
            SecurityType.YANDEX -> userAccountRepository.getInfoYandexUser()
            SecurityType.VK -> userAccountRepository.getInfoVkUser()
            else -> Result.failure(ClientException())
        }
    }
}