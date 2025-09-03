package com.mordva.room.external

import com.mordva.model.movie.Movie

interface MovieLocalService {
    suspend fun insert(movie: Movie)
    suspend fun getMovie(movieId: Int): Movie?
}