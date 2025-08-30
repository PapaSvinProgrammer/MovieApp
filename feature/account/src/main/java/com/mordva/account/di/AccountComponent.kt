package com.mordva.account.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [AccountModule::class],
    dependencies = [AccountDependency::class]
)
@AccountScope
internal interface AccountComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: AccountDependency): AccountComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}