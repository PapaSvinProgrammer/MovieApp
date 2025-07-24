package com.example.movieScreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.collectionusecase.GetCollectionBySlug
import com.example.comment.GetCommentByDate
import com.example.data.external.CollectionRepository
import com.example.data.external.CommentRepository
import com.example.data.external.MovieRepository
import com.example.data.external.di.DataModule
import com.example.movieScreen.GetMovieById
import com.example.movieScreen.GetMovieImages
import com.example.movieScreen.presentation.MovieViewModel
import com.example.viewmodelfactory.ViewModelFactory
import com.example.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [DataModule::class]
)
interface MovieModule {
    @Binds
    @MovieScope
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @MovieScope
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindsMovieViewModel(viewModel: MovieViewModel): ViewModel

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