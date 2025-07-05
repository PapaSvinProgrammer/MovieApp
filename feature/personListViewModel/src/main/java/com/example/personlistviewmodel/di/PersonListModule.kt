package com.example.personlistviewmodel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.external.PersonRepository
import com.example.data.external.di.DataModule
import com.example.person.GetPersonByFilter
import com.example.personlistviewmodel.PersonListViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
interface PersonListModule {
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