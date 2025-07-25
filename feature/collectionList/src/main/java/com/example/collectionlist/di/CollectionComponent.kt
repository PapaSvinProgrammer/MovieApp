package com.example.collectionlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [CollectionModule::class],
    dependencies = [AppComponent::class]
)
@CollectionScope
interface CollectionComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CollectionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}