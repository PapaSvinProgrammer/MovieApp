package com.example.movieapp.app.utils

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json

object CustomNavType {
    val LinkedHashMapType = object: NavType<LinkedHashMap<String, String>>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: LinkedHashMap<String, String>) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): LinkedHashMap<String, String>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun serializeAsValue(value: LinkedHashMap<String, String>): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun parseValue(value: String): LinkedHashMap<String, String> {
            return Json.decodeFromString(Uri.decode(value))
        }
    }
}