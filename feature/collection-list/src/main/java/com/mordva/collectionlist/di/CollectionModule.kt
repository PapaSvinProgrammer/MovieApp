package com.mordva.collectionlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.collectionlist.presentation.CollectionListViewModel
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.usecase.collection.GetCollectionAll
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface CollectionModule {
    @Binds
    @CollectionScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @CollectionScope
    @IntoMap
    @ViewModelKey(CollectionListViewModel::class)
    fun bindsCollectionListViewModel(viewModel: CollectionListViewModel): ViewModel

    companion object {
        @Provides
        @CollectionScope
        fun providesGetCollectionAll(repository: CollectionRepository): GetCollectionAll {
            return GetCollectionAll(repository)
        }

        @Provides
        @CollectionScope
        fun providesGetCollectionByCategory(repository: CollectionRepository): GetCollectionByCategory {
            return GetCollectionByCategory(repository)
        }
    }
}