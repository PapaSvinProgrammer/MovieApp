package com.example.settings.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [SettingsModule::class],
    dependencies = [AppComponent::class]
)
@SettingsScope
interface SettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SettingsComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}
