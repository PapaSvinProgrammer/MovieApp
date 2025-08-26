package com.mordva.awardlist.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [AwardListModule::class],
    dependencies = [AwardListDependency::class]
)
@AwardListScope
interface AwardListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AwardListDependency): AwardListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}