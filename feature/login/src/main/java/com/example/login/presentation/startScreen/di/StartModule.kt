package com.example.login.presentation.startScreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.di.DataModule
import com.example.login.presentation.startScreen.StartViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
interface StartModule {
    @Binds
    @StartScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @StartScope
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun bindsStartViewModel(viewModel: StartViewModel): ViewModel
}