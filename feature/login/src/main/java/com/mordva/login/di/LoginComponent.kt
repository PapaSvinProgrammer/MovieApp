package com.mordva.login.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [LoginModule::class],
    dependencies = [LoginDependency::class]
)
@LoginScope
internal interface LoginComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: LoginDependency): LoginComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}