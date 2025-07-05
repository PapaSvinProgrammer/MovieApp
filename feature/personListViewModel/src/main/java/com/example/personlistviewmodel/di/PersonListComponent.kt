package com.example.personlistviewmodel.di

import com.example.personlistviewmodel.PersonListViewModel
import dagger.Component

@Component(
    modules = [PersonListModule::class]
)
@PersonListScope
interface PersonListComponent {
    @Component.Factory
    interface Factory {
        fun create(): PersonListComponent
    }

    val viewModel: PersonListViewModel
}