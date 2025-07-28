package com.example.personscreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [PersonModule::class],
    dependencies = [AppComponent::class]
)
@PersonScope
interface PersonComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): PersonComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}