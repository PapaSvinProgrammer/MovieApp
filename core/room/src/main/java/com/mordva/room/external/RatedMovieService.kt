package com.mordva.room.external

import com.mordva.model.local.RatedMovie
import kotlinx.coroutines.flow.Flow

interface RatedMovieService {
    suspend fun insert(movie: RatedMovie)
    suspend fun delete(id: Int)
    fun getById(id: Int): Flow<RatedMovie?>
    fun getAll(): Flow<List<RatedMovie>>
    fun getAllByRating(rating: Int): Flow<List<RatedMovie>>
}