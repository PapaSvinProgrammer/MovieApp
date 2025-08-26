package com.mordva.base_view_models.movie_list
import com.mordva.domain.repository.MovieRepository

interface MovieListDependency {
    val movieRepository: MovieRepository
}