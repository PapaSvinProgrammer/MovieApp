package com.example.login.presentation.loginScreen.di

import com.example.data.external.di.DataModule
import dagger.Module

@Module(
    includes = [DataModule::class]
)
internal interface LoginModule