package com.example.personlistviewmodel.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [PersonListModule::class],
    dependencies = [PersonListDependency::class]
)
@PersonListScope
interface PersonListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: PersonListDependency): PersonListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}