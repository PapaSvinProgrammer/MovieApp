package com.example.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.presentation.HomeViewModel
import com.example.movieapp.MainViewModel
import com.example.search.searchScreen.SearchViewModel
import com.example.settings.SettingsViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface AppModule {
    @Binds
    @Singleton
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(SearchViewModel::class)
    fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel
}