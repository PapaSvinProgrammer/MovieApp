package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent {
    @Component.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}