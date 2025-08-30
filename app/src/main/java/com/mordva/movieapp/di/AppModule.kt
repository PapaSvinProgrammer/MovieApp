package com.mordva.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.movieapp.main.MainViewModel
import com.mordva.util.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule {
    @Binds
    @ApplicationScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ApplicationScope
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsViewModel(viewModel: MainViewModel): ViewModel
}