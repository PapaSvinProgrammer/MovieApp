import java.io.FileInputStream
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}

val properties = Properties()
val file = rootProject.file("local.properties")
properties.load(FileInputStream(file))

extra.apply {
    set("movieApiKey", getKinopoiskAPIKey())
}

private fun getKinopoiskAPIKey(): String {
    return properties.getProperty("MOVIE_API_KEY", "")
}