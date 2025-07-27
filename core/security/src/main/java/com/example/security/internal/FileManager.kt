package com.example.security.internal

import android.content.Context
import java.io.File
import javax.inject.Inject

internal class FileManager @Inject constructor(
    private val context: Context
) {
    private fun getFile(fileName: String): File {
        return File(context.filesDir, fileName)
    }

    fun writeToFile(content: String, fileName: String) {
        getFile(fileName).writeText(content)
    }

    fun readFromFile(fileName: String): String? {
        return if (fileExists(fileName)) {
            getFile(fileName).readText()
        } else {
            null
        }
    }

    fun fileExists(fileName: String): Boolean {
        return getFile(fileName).exists()
    }

    fun deleteFile(fileName: String): Boolean {
        return if (fileExists(fileName)) {
            getFile(fileName).delete()
        } else {
            false
        }
    }
}
