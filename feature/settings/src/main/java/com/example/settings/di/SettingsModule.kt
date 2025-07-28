package com.example.settings.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.settings.presentation.confidential.ConfidentialViewModel
import com.example.settings.presentation.decor.DecorViewModel
import com.example.settings.presentation.sound.SoundViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SettingsModule {
    @Binds
    @SettingsScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(DecorViewModel::class)
    fun bindsDecorViewModel(viewModel: DecorViewModel): ViewModel

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(SoundViewModel::class)
    fun bindsSoundViewModel(viewModel: SoundViewModel): ViewModel

    @Binds
    @SettingsScope
    @IntoMap
    @ViewModelKey(ConfidentialViewModel::class)
    fun bindsConfidentialViewModel(viewModel: ConfidentialViewModel): ViewModel
}