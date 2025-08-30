package com.mordva.movieapp.app

import android.app.Application
import android.content.Context
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import com.mordva.movieapp.di.AppComponent
import com.mordva.movieapp.di.DaggerAppComponent
import com.vk.id.VKID

class App: Application(), SingletonImageLoader.Factory {
    lateinit var appComponent: AppComponent
    private val lazyLoader: dagger.Lazy<ImageLoader> by lazy {
        appComponent.lazyImageLoader()
    }

    override fun onCreate() {
        super.onCreate()

        VKID.init(this)
        appComponent = DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return lazyLoader.get()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }
