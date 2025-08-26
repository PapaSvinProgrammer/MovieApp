package com.mordva.otpscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.otpscreen.presentation.OtpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface OtpModule {
    @Binds
    @OtpScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @OtpScope
    @IntoMap
    @ViewModelKey(OtpViewModel::class)
    fun bindsOtpViewModel(viewModel: OtpViewModel): ViewModel
}
