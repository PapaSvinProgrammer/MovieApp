package com.mordva.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListTypePair = object : NavType<List<Pair<String, String>>>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: List<Pair<String, String>>) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): List<Pair<String, String>>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): List<Pair<String, String>> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: List<Pair<String, String>>): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}

inline fun <reified T> customType() : NavType<T> {
    return object : NavType<T>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): T? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: T): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}