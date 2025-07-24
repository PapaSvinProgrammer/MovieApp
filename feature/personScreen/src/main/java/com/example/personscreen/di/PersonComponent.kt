package com.example.personscreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [PersonModule::class]
)
@PersonScope
interface PersonComponent {
    @Component.Factory
    interface Factory {
        fun create(): PersonComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}