package com.example.movieapp

import android.app.Application
import com.example.corecomponent.AppComponent
import com.example.movieapp.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}