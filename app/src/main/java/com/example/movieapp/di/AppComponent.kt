package com.example.movieapp.di

import android.content.Context
import com.example.data.external.di.DataModule
import com.example.movieapp.MainActivity
import com.example.network.external.di.NetworkModule
import com.example.room.internal.di.RoomModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}