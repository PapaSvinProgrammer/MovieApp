package com.mordva.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface LoginModule {
    @Binds
    @LoginScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @LoginScope
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun  bindsLoginViewModel(viewModel: LoginViewModel): ViewModel
}