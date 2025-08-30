package com.mordva.account.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserYandexDto(
    val id: String,
    val login: String,

    @SerialName("client_id")
    val clientId: String,

    @SerialName("display_name")
    val displayName: String,

    @SerialName("real_name")
    val realName: String,

    @SerialName("default_email")
    val defaultEmail: String,

    val emails: List<String>,

    @SerialName("default_avatar_id")
    val defaultAvatarId: String,

    @SerialName("is_avatar_empty")
    val isAvatarEmpty: Boolean,

    val psuid: String
)