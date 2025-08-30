package com.mordva.account.data.service.external

internal interface RefreshTokenService {
    suspend fun refreshVkToken(): Result<String>
    suspend fun refreshYandexToken(): Result<String>
}