package com.mordva.movieScreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.usecase.collection.GetCollectionBySlug
import com.mordva.domain.usecase.comment.GetCommentByDate
import com.mordva.domain.usecase.movie.GetMovieById
import com.mordva.domain.usecase.movie.GetMovieImages
import com.mordva.movieScreen.presentation.groupPerson.GroupPersonViewModel
import com.mordva.movieScreen.presentation.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal interface MovieModule {
    @Binds
    @MovieScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @MovieScope
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindsMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @MovieScope
    @IntoMap
    @ViewModelKey(GroupPersonViewModel::class)
    fun bindsGroupPersonViewModel(viewModel: GroupPersonViewModel): ViewModel

    companion object {
        @Provides
        @MovieScope
        fun providesGetMovieById(repository: MovieRepository): GetMovieById {
            return GetMovieById(repository)
        }

        @Provides
        @MovieScope
        fun providesGetMovieImages(repository: MovieRepository): GetMovieImages {
            return GetMovieImages(repository)
        }

        @Provides
        @MovieScope
        fun providesGetCollectionBySlug(repository: CollectionRepository): GetCollectionBySlug {
            return GetCollectionBySlug(repository)
        }

        @Provides
        @MovieScope
        fun providesGetCommentByDate(repository: CommentRepository): GetCommentByDate {
            return GetCommentByDate(repository)
        }
    }
}