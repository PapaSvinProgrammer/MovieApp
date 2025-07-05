package com.example.login.loginScreen.di

import dagger.Component

@Component(
    modules = [LoginModule::class]
)
@LoginScope
interface LoginComponent