package com.example.search.searchSettings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.internal.di.DataModule
import com.example.search.searchSettings.SearchSettingsViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
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