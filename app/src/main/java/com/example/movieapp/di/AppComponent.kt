package com.example.movieapp.di

import android.content.Context
import com.example.core.di.RepositoryModule
import com.example.movieapp.MainActivity
import com.example.movieapp.di.viewModel.ViewModelFactoryModule
import com.example.movieapp.di.viewModel.ViewModelModule
import com.example.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}