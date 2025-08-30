package com.mordva.security.internal.util

import androidx.datastore.preferences.core.stringPreferencesKey

internal fun SecurityType.toPreferencesKey() = when (this) {
    SecurityType.YANDEX -> stringPreferencesKey("yandex")
    SecurityType.VK -> stringPreferencesKey("vk")
    SecurityType.PASSWORD -> stringPreferencesKey("password")
}
