package com.mordva.personscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.usecase.awards.GetPersonAwardsByDate
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.domain.usecase.person.GetPersonById
import com.mordva.personscreen.presentation.PersonViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
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