package com.mordva.account.data.repository

import com.mordva.account.data.service.UserAccountService
import com.mordva.account.domain.model.UserAccount
import com.mordva.account.domain.repository.UserAccountRepository
import com.mordva.security.external.SecurityRepository
import com.mordva.security.internal.util.SecurityType
import javax.inject.Inject

internal class UserAccountRepositoryImpl @Inject constructor(
    private val securityRepository: SecurityRepository,
    private val userAccountService: UserAccountService
) : UserAccountRepository {
    override suspend fun getInfoYandexUser(): Result<UserAccount> {
        val token = securityRepository.getData(SecurityType.YANDEX).getOrDefault("")
        return userAccountService.getYandexUser(token)
    }

    override suspend fun getInfoVkUser(): Result<UserAccount> {
        val token = securityRepository.getData(SecurityType.VK).getOrDefault("")
        return userAccountService.getVkUser(token)
    }
}