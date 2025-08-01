package com.example.movielistviewmodel.di

import com.example.data.external.MovieRepository

interface MovieListDependency {
    val movieRepository: MovieRepository
}