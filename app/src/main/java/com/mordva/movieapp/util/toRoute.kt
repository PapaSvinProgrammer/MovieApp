package com.mordva.movieapp.util

import com.mordva.navigation.HomeGraph
import com.mordva.navigation.LoginGraph
import com.mordva.navigation.OtpGraph
import com.mordva.security.internal.util.SecurityType

fun SecurityType?.toRoute() = when (this) {
    SecurityType.YANDEX -> HomeGraph
    SecurityType.VK -> HomeGraph
    SecurityType.PASSWORD -> OtpGraph
    null -> LoginGraph
}