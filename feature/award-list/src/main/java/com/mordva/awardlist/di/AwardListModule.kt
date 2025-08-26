package com.mordva.awardlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.awardlist.presentation.AwardListViewModel
import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.usecase.awards.GetMovieAwardsByDate
import com.mordva.domain.usecase.awards.GetMovieAwardsByTitle
import com.mordva.domain.usecase.awards.GetPersonAwardsByDate
import com.mordva.domain.usecase.awards.GetPersonAwardsByTitle
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface AwardListModule {
    @Binds
    @AwardListScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @AwardListScope
    @IntoMap
    @ViewModelKey(AwardListViewModel::class)
    fun bindsAwardListViewModel(viewModel: AwardListViewModel): ViewModel

    companion object {
        @Provides
        @AwardListScope
        fun providesGetMovieAwardByDate(repository: AwardRepository): GetMovieAwardsByDate {
            return GetMovieAwardsByDate(repository)
        }

        @Provides
        @AwardListScope
        fun providesGetMovieAwardByTitle(repository: AwardRepository): GetMovieAwardsByTitle {
            return GetMovieAwardsByTitle(repository)
        }

        @Provides
        @AwardListScope
        fun providesGetPersonAwardByDate(repository: AwardRepository): GetPersonAwardsByDate {
            return GetPersonAwardsByDate(repository)
        }

        @Provides
        @AwardListScope
        fun providesGetPersonAwardByTitle(repository: AwardRepository): GetPersonAwardsByTitle {
            return GetPersonAwardsByTitle(repository)
        }
    }
}