package com.mordva.account.data.service.internal

import com.mordva.account.data.service.external.RefreshTokenService
import com.mordva.util.error.ClientException
import com.mordva.util.error.ServerException
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.refresh.VKIDRefreshTokenCallback
import com.vk.id.refresh.VKIDRefreshTokenFail
import javax.inject.Inject

internal class RefreshTokenServiceImpl @Inject constructor(): RefreshTokenService {
    override suspend fun refreshVkToken(): Result<String> {
        var res = Result.failure<String>(ClientException())

        val callback = object : VKIDRefreshTokenCallback {
            override fun onSuccess(token: AccessToken) {
                res = Result.success(token.token)
            }

            override fun onFail(fail: VKIDRefreshTokenFail) {
                res = Result.failure(ServerException(fail.description))
            }
        }

        VKID.instance.refreshToken(callback)
        return res
    }

    override suspend fun refreshYandexToken(): Result<String> {
        return Result.failure(ClientException())
    }
}