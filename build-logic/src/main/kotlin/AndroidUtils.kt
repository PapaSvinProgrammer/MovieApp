import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

object Const {

    const val NAMESPACE = "com.example.movieapp"
    const val COMPILE_SKD = 35
    const val MIN_SKD = 26
    val COMPILE_JDK_VERSION = JavaVersion.VERSION_17
}

fun BaseExtension.baseAndroidConfig(project: Project) {
    val safeProjectName = project.name.replace("-", "_")
    namespace = "${Const.NAMESPACE}.$safeProjectName"
    setCompileSdkVersion(Const.COMPILE_SKD)
    defaultConfig {
        minSdk = Const.MIN_SKD

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Const.COMPILE_JDK_VERSION
        targetCompatibility = Const.COMPILE_JDK_VERSION
    }
}

internal val Project.libs: LibrariesForLibs
    get() = (this as ExtensionAware).extensions.getByName("libs") as LibrariesForLibs