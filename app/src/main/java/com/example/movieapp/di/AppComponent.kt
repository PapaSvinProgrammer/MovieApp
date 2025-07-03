package com.example.movieapp.di

import android.content.Context
import com.example.data.internal.di.DataModule
import com.example.movieapp.MainActivity
import com.example.movieapp.di.viewModel.ViewModelFactoryModule
import com.example.movieapp.di.viewModel.ViewModelModule
import com.example.network.internal.di.NetworkModule
import com.example.room.internal.di.RoomModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        ViewModelModule::class,
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