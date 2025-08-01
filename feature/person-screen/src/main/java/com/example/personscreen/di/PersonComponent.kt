package com.example.personscreen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [PersonModule::class],
    dependencies = [PersonDependency::class]
)
@PersonScope
interface PersonComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: PersonDependency): PersonComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}