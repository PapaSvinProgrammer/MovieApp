package com.example.data.internal.di

import dagger.Module

@Module(
    includes = [DataModuleImpl::class]
)
interface DataModule