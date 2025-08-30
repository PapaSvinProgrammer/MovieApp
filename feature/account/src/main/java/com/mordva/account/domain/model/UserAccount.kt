package com.mordva.account.domain.model

import com.mordva.security.internal.util.SecurityType

internal data class UserAccount(
    val name: String,
    val email: String,
    val image: String,
    val securityType: SecurityType
)