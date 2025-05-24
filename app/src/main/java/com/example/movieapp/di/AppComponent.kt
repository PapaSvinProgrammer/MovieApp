package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.MainActivity
import com.example.movieapp.di.viewModel.ViewModelFactoryModule
import com.example.movieapp.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}