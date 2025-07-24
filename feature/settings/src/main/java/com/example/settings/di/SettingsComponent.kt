package com.example.settings.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SettingsModule::class]
)
@SettingsScope
interface SettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}