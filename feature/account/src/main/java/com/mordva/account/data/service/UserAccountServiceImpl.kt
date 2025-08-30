package com.mordva.account.data.service

import android.util.Log
import com.mordva.account.data.mapper.toDomain
import com.mordva.account.data.model.UserYandexDto
import com.mordva.account.domain.model.UserAccount
import com.mordva.network.internal.core.safeCall
import com.mordva.util.error.ClientException
import com.vk.id.VKID
import com.vk.id.VKIDUser
import com.vk.id.refreshuser.VKIDGetUserCallback
import com.vk.id.refreshuser.VKIDGetUserFail
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.takeFrom
import javax.inject.Inject

internal class UserAccountServiceImpl @Inject constructor(
    private val client: HttpClient
) : UserAccountService {
    override suspend fun getYandexUser(token: String): Result<UserAccount> {
        return safeCall<UserYandexDto> {
            client.get("https://login.yandex.ru/info") {
                headers.clear()
                header("Authorization", "OAuth $token")
            }
        }.map { it.toDomain() }
    }

    override suspend fun getVkUser(token: String): Result<UserAccount> {
        val callback = object : VKIDGetUserCallback {
            override fun onSuccess(user: VKIDUser) {
                Log.d("RRRR", user.toString())
            }

            override fun onFail(fail: VKIDGetUserFail) {
                Log.d("RRRR", fail.toString())
            }
        }

        VKID.instance.getUserData(callback)

        return Result.failure(ClientException())
    }
}