package com.example.personscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awards.GetPersonAwardsByDate
import com.example.data.external.AwardRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository
import com.example.data.internal.di.DataModule
import com.example.movieScreen.GetMovieByFilter
import com.example.person.GetPersonById
import com.example.personscreen.presentation.PersonViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
internal interface PersonModule {
    @Binds
    @PersonScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @PersonScope
    @IntoMap
    @ViewModelKey(PersonViewModel::class)
    fun bindsPersonViewModel(viewModel: PersonViewModel): ViewModel

    companion object {
        @Provides
        @PersonScope
        fun providesGetPersonById(repository: PersonRepository): GetPersonById {
            return GetPersonById(repository)
        }

        @Provides
        @PersonScope
        fun providesGetMovieByFilter(repository: MovieRepository): GetMovieByFilter {
            return GetMovieByFilter(repository)
        }

        @Provides
        @PersonScope
        fun providesGetPersonAwardsByDate(repository: AwardRepository): GetPersonAwardsByDate {
            return GetPersonAwardsByDate(repository)
        }
    }
}