package com.mordva.search.presentation.searchSettings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.search.presentation.searchSettings.SearchSettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SearchSettingsModule {
    @Binds
    @SearchSettingsScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SearchSettingsScope
    @IntoMap
    @ViewModelKey(SearchSettingsViewModel::class)
    fun bindsSearchSettingsViewModel(viewModel: SearchSettingsViewModel): ViewModel
}