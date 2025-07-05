package com.example.movieapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.di.DataModule
import com.example.home.presentation.HomeViewModel
import com.example.movieapp.MainActivity
import com.example.network.external.di.NetworkModule
import com.example.room.internal.di.RoomModule
import com.example.viewmodelfactory.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    val viewModelFactory: ViewModelProvider.Factory
}