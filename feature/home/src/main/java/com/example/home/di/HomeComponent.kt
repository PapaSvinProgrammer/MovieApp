package com.example.home.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [AppComponent::class]
)
@HomeScope
interface HomeComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): HomeComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}