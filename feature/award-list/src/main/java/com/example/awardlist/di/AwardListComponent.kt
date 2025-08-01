package com.example.awardlist.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [AwardListModule::class],
    dependencies = [AwardDependency::class]
)
@AwardListScope
interface AwardListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AwardDependency): AwardListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}