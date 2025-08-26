package com.mordva.data.di

import dagger.Module

@Module(
    includes = [DataModuleImpl::class]
)
interface DataModule