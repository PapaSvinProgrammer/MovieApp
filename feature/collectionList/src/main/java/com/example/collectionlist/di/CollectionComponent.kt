package com.example.collectionlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.collectionlist.CollectionListViewModel
import dagger.Component

@Component(
    modules = [
        CollectionModule::class
    ]
)
@CollectionScope
interface CollectionComponent {
    @Component.Factory
    interface Factory {
        fun create(): CollectionComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}