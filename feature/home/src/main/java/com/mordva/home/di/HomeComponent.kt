package com.mordva.home.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [HomeDependency::class]
)
@HomeScope
interface HomeComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: HomeDependency): HomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}