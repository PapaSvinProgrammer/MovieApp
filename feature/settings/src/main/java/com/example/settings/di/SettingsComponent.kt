package com.example.settings.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [SettingsModule::class],
    dependencies = [SettingsDependency::class]
)
@SettingsScope
interface SettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: SettingsDependency): SettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}
