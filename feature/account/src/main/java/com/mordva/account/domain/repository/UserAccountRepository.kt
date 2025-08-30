package com.mordva.account.domain.repository

import com.mordva.account.domain.model.UserAccount

internal interface UserAccountRepository {
    suspend fun getInfoYandexUser(): Result<UserAccount>
    suspend fun getInfoVkUser(): Result<UserAccount>
}