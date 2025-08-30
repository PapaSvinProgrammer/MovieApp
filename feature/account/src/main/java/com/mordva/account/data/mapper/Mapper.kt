package com.mordva.account.data.mapper

import com.mordva.account.data.model.UserYandexDto
import com.mordva.account.domain.model.UserAccount
import com.mordva.security.internal.util.SecurityType
import com.vk.id.VKIDUser

internal fun UserYandexDto.toDomain() = UserAccount(
    name = displayName,
    email = defaultEmail,
    image = "https://avatars.yandex.net/get-yapic/$defaultAvatarId/50x50",
    securityType = SecurityType.YANDEX
)

internal fun VKIDUser.toDomain() = UserAccount(
    email = email ?: "",
    name = "$firstName $lastName",
    image = photo200 ?: "",
    securityType = SecurityType.VK
)