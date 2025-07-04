package com.example.awardlist.di

import com.example.awardlist.AwardListViewModel
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

    val viewModel: AwardListViewModel
}