package com.mordva.search.presentation.searchScreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.domain.usecase.movie.SearchMovie
import com.mordva.domain.usecase.person.GetPersonByFilter
import com.mordva.domain.usecase.person.SearchPerson
import com.mordva.search.presentation.searchScreen.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface SearchModule {
    @Binds
    @SearchScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SearchScope
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    companion object {
        @Provides
        @SearchScope
        fun providesGetCollectionByCategory(repository: CollectionRepository): GetCollectionByCategory {
            return GetCollectionByCategory(repository)
        }

        @Provides
        @SearchScope
        fun providesGetPersonByFilter(repository: PersonRepository): GetPersonByFilter {
            return GetPersonByFilter(repository)
        }

        @Provides
        @SearchScope
        fun providesSearchPerson(repository: PersonRepository): SearchPerson {
            return SearchPerson(repository)
        }

        @Provides
        @SearchScope
        fun providesGetMovieByFilter(repository: MovieRepository): GetMovieByFilter {
            return GetMovieByFilter(repository)
        }

        @Provides
        @SearchScope
        fun providesSearchMovie(repository: MovieRepository): SearchMovie {
            return SearchMovie(repository)
        }
    }
}