package com.mordva.home.di

import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.MovieRepository

interface HomeDependency {
    val movieRepository: MovieRepository
    val collectionRepository: CollectionRepository
}
