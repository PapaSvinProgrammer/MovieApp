package com.example.search.searchScreen.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SearchModule::class]
)
@SearchScope
interface SearchComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SearchComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}