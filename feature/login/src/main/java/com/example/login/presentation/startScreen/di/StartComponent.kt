package com.example.login.presentation.startScreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [StartModule::class]
)
@StartScope
internal interface StartComponent {
    @Component.Factory
    interface Factory {
        fun create(): StartComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}