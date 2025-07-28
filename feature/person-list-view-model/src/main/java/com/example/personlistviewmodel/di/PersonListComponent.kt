package com.example.personlistviewmodel.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import com.example.personlistviewmodel.PersonListViewModel
import dagger.Component

@Component(
    modules = [PersonListModule::class],
    dependencies = [AppComponent::class]
)
@PersonListScope
interface PersonListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): PersonListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}