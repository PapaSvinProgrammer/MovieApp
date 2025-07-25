package com.example.awardlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.corecomponent.AppComponent
import dagger.Component

@Component(
    modules = [AwardListModule::class],
    dependencies = [AppComponent::class]
)
@AwardListScope
interface AwardListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): AwardListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}