package com.mordva.room.internal.rated

import com.mordva.model.local.RatedMovie
import com.mordva.room.external.RatedMovieService
import com.mordva.room.internal.utils.toDomain
import com.mordva.room.internal.utils.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RatedMovieServiceImpl @Inject constructor(
    private val dao: RatedMovieDao
) : RatedMovieService {
    override suspend fun insert(movie: RatedMovie) {
        dao.insert(movie.toEntity())
    }

    override suspend fun delete(id: Int) {
        dao.delete(id)
    }

    override fun isStock(id: Int): Flow<RatedMovie?> {
        return dao.isStock(id).map { it?.toDomain() }
    }

    override fun getAll(): Flow<List<RatedMovie>> {
        return dao.getAll().map { it.toDomain() }
    }

    override fun getAllByRating(rating: Int): Flow<List<RatedMovie>> {
        return dao.getAllByRating(rating).map { it.toDomain() }
    }
}
