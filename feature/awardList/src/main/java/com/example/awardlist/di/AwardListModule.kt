package com.example.awardlist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awardlist.AwardListViewModel
import com.example.awards.GetMovieAwardsByDate
import com.example.awards.GetMovieAwardsByTitle
import com.example.awards.GetPersonAwardsByDate
import com.example.awards.GetPersonAwardsByTitle
import com.example.data.external.AwardRepository
import com.example.data.external.di.DataModule
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        DataModule::class
    ]
)
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
            return  GetPersonAwardsByTitle(repository)
        }
    }
}