package com.example.login.startScreen.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.login.startScreen.StartViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [StartModule::class]
)
@StartScope
interface StartComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): StartComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}