package com.example.login.presentation.startScreen.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [StartModule::class]
)
@StartScope
interface StartComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): StartComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}