package com.example.data.external.di

import com.example.data.internal.di.DataModuleImpl
import com.example.network.external.di.NetworkModule
import com.example.room.internal.di.RoomModule
import dagger.Module

@Module(
    includes = [
        DataModuleImpl::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
interface DataModule