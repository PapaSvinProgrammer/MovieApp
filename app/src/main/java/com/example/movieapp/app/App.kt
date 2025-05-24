package com.example.movieapp.app

import android.app.Application
import com.example.movieapp.di.AppComponent
import com.example.movieapp.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}