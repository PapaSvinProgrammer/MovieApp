package com.example.personscreen.di

import com.example.personscreen.PersonViewModel
import dagger.Component

@Component(
    modules = [PersonModule::class]
)
@PersonScope
interface PersonComponent {
    @Component.Factory
    interface Factory {
        fun create(): PersonComponent
    }

    val viewModel: PersonViewModel
}