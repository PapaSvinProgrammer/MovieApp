package com.example.navigationroute

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.navigationroute.model.WatchabilityScreenObject
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

    val WatchabilityType = object : NavType<WatchabilityScreenObject>(
        isNullableAllowed = false
    ) {
        override fun put(
            bundle: SavedState,
            key: String,
            value: WatchabilityScreenObject
        ) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(
            bundle: SavedState,
            key: String
        ): WatchabilityScreenObject? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): WatchabilityScreenObject {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: WatchabilityScreenObject): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}
