package com.mordva.images_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.images_list.presentation.ImageListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ImageListModule {
    @Binds
    @ImageListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ImageListScope
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    fun bindsViewModel(viewModel: ImageListViewModel): ViewModel
}