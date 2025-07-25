package com.example.collectionlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.collectionlist.presentation.CollectionListViewModel
import com.example.collectionusecase.GetCollectionAll
import com.example.collectionusecase.GetCollectionByCategory
import com.example.data.external.CollectionRepository
import com.example.data.internal.di.DataModule
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
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