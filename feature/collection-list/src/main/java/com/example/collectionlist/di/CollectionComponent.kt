package com.example.collectionlist.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [CollectionModule::class],
    dependencies = [CollectionDependency::class]
)
@CollectionScope
interface CollectionComponent {
    @Component.Factory
    interface Factory {
        fun create(dependency: CollectionDependency): CollectionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}