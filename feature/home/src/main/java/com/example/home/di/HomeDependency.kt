package com.example.home.di

import com.example.data.external.CollectionRepository
import com.example.data.external.MovieRepository

interface HomeDependency {
    val movieRepository: MovieRepository
    val collectionRepository: CollectionRepository
}
