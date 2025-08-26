package com.mordva.base_view_models.person_list

import androidx.lifecycle.ViewModelProvider
import dagger.Component

@Component(
    modules = [PersonListModule::class],
    dependencies = [PersonListDependency::class]
)
@PersonListScope
interface PersonListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: PersonListDependency): PersonListComponent
    }

    val viewModelFactory: ViewModelProvider.Factory
}