package com.mordva.account.data.repository

import com.mordva.account.data.service.external.RefreshTokenService
import com.mordva.account.data.service.external.UserAccountService
import com.mordva.account.domain.model.UserAccount
import com.mordva.account.domain.repository.UserAccountRepository
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import com.mordva.util.error.ClientException
import javax.inject.Inject

internal class UserAccountRepositoryImpl @Inject constructor(
    private val securityRepository: SecurityRepository,
    private val userAccountService: UserAccountService,
    private val refreshTokenService: RefreshTokenService
) : UserAccountRepository {
    override suspend fun getInfoYandexUser(): Result<UserAccount> {
        val token = securityRepository.getData(SecurityType.YANDEX).getOrDefault("")
        return userAccountService.getYandexUser(token)
    }

    override suspend fun getInfoVkUser(): Result<UserAccount> {
        val token = securityRepository.getData(SecurityType.VK).getOrDefault("")
        val res = userAccountService.getVkUser(token)

        if (res.isSuccess) {
            return res
        }

        refreshTokenService.refreshVkToken().onSuccess { vkToken ->
            return userAccountService.getVkUser(token)
        }.onFailure { error ->
            return Result.failure(error)
        }

        return Result.failure(ClientException())
    }
}