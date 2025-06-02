package com.example.movieapp.app.utils

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json

object CustomNavType {
    val ArrayListType = object: NavType<ArrayList<Pair<String, String>>>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: ArrayList<Pair<String, String>>) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): ArrayList<Pair<String, String>>? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): ArrayList<Pair<String, String>> {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: ArrayList<Pair<String, String>>): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}