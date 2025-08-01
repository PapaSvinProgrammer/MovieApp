package com.example.login.presentation.loginScreen.di

import dagger.Component

@Component(
    modules = [LoginModule::class]
)
@LoginScope
internal interface LoginComponent