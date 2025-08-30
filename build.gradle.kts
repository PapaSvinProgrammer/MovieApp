import java.io.FileInputStream
import java.util.Properties

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

val properties = Properties()
val file = rootProject.file("local.properties")
properties.load(FileInputStream(file))

vkidManifestPlaceholders {
    val clientId = getVkClientId()
    val secretId = getVkClientSecret()

    init(
        clientId = clientId,
        clientSecret = secretId,
    )
}

extra.apply {
    set("movieApiKey", getKinopoiskAPIKey())
    set("yandexAuthKey", getYandexAuthKey())
}

private fun getKinopoiskAPIKey() = properties.getProperty("MOVIE_API_KEY", "")
private fun getYandexAuthKey() = properties.getProperty("YANDEX_AUTH", "")
private fun getVkClientId() = properties.getProperty("VK_CLIENT_ID", "")
private fun getVkClientSecret() = properties.getProperty("VK_CLIENT_SECRET", "")