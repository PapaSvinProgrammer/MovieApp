package com.example.data.external.di

import com.example.data.internal.di.DataModuleImpl
import dagger.Module

@Module(
    includes = [DataModuleImpl::class]
)
interface DataModule