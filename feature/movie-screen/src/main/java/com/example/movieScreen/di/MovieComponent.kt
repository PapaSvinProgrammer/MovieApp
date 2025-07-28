package com.example.movieScreen.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [MovieModule::class],
    dependencies = [AppComponent::class]
)
@MovieScope
interface MovieComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MovieComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}