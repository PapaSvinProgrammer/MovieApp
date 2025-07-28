package com.example.search.searchScreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.collectionusecase.GetCollectionByCategory
import com.example.data.external.CollectionRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository
import com.example.movieScreen.GetMovieByFilter
import com.example.movieScreen.SearchMovie
import com.example.person.GetPersonByFilter
import com.example.person.SearchPerson
import com.example.search.searchScreen.SearchViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
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