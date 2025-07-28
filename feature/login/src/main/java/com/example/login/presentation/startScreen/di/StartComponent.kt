package com.example.login.presentation.startScreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [StartModule::class],
    dependencies = [AppComponent::class]
)
@StartScope
interface StartComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): StartComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}