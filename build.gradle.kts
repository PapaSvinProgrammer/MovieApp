import java.io.FileInputStream
import java.util.Properties
import kotlin.toString

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.graph) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}

vkidManifestPlaceholders {
    init(
        clientId = "54088753",
        clientSecret = "e251KqhibxwErk8ICPVM",
    )

    vkidRedirectHost = "vk.com"
    vkidRedirectScheme = "vk54088753"
    vkidClientId = "54088753"
    vkidClientSecret = "e251KqhibxwErk8ICPVM"
}

val properties = Properties()
val file = rootProject.file("local.properties")
properties.load(FileInputStream(file))

extra.apply {
    set("movieApiKey", getKinopoiskAPIKey())
    set("yandexAuthKey", getYandexAuthKey())
    set("vkAuthKey", getVkAuthKey())
}

private fun getKinopoiskAPIKey() = properties.getProperty("MOVIE_API_KEY", "")
private fun getYandexAuthKey() = properties.getProperty("YANDEX_AUTH", "")
private fun getVkAuthKey() = properties.getProperty("VK_AUTH", "")