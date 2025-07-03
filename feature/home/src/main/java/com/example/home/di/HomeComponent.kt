package com.example.home.di

import com.example.home.presentation.HomeViewModel
import dagger.Component

@Component(
    modules = [HomeModule::class]
)
@HomeScope
interface HomeComponent {
    @Component.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    val viewModel: HomeViewModel
}