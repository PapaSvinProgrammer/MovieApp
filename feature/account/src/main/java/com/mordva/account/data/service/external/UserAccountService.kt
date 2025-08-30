package com.mordva.account.data.service.external

import com.mordva.account.domain.model.UserAccount

internal interface UserAccountService {
    suspend fun getYandexUser(token: String): Result<UserAccount>
    suspend fun getVkUser(token: String): Result<UserAccount>
}