package com.mordva.data

import com.mordva.domain.repository.RatedMovieRepository
import com.mordva.model.local.RatedMovie
import com.mordva.room.external.RatedMovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RatedMovieRepositoryImpl @Inject constructor(
    private val service: RatedMovieService
) : RatedMovieRepository {
    override suspend fun add(ratedMovie: RatedMovie) {
        service.insert(ratedMovie)
    }

    override suspend fun delete(movieId: Int) {
        service.delete(movieId)
    }

    override fun isStock(movieId: Int): Flow<RatedMovie?> {
        return service.isStock(movieId)
    }

    override fun getAll(): Flow<List<RatedMovie>> {
        return service.getAll()
    }

    override fun getAllByRating(rating: Int): Flow<List<RatedMovie>> {
        return service.getAllByRating(rating)
    }
}
