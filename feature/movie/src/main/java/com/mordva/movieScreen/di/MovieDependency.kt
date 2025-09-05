package com.mordva.movieScreen.di

import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.CommentRepository
import com.mordva.domain.repository.FavoritePackageRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.domain.repository.WillWatchPackageRepository
import com.mordva.room.external.MovieLocalService

interface MovieDependency {
    val movieRepository: MovieRepository
    val collectionRepository: CollectionRepository
    val commentRepository: CommentRepository
    val personRepository: PersonRepository
    val ratedMovieRepository: RatedMovieRepository
    val movieLocalService: MovieLocalService
    val willWatchPackageRepository: WillWatchPackageRepository
    val favoritePackageRepository: FavoritePackageRepository
}