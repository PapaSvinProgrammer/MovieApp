package com.mordva.base_view_models.person_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.usecase.person.GetPersonByFilter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface PersonListModule {
    @Binds
    @PersonListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @PersonListScope
    @IntoMap
    @ViewModelKey(PersonListViewModel::class)
    fun bindsPersonListViewModel(viewModel: PersonListViewModel): ViewModel

    companion object {
        @Provides
        @PersonListScope
        fun provides(repository: PersonRepository): GetPersonByFilter {
            return GetPersonByFilter(repository)
        }
    }
}