package com.example.awardlist.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [
        AwardListModule::class
    ]
)
@AwardListScope
interface AwardListComponent {
    @Component.Factory
    interface Factory {
        fun create(): AwardListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}