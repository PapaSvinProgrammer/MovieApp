package com.mordva.account.data.mapper

import com.mordva.account.data.model.UserYandexDto
import com.mordva.account.domain.model.UserAccount

internal fun UserYandexDto.toDomain() = UserAccount(
    name = displayName,
    email = defaultEmail,
    image = "https://avatars.yandex.net/get-yapic/$defaultAvatarId/50x50"
)