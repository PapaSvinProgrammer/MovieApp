package com.mordva.movieapp

import android.app.Application
import android.content.Context
import com.mordva.movieapp.di.AppComponent
import com.mordva.movieapp.di.DaggerAppComponent
import com.vk.id.VKID

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        VKID.init(this)
        appComponent = DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }
