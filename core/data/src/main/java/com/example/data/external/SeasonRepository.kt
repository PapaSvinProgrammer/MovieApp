package com.example.data.external

import com.example.model.season.Season

interface SeasonRepository {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>>
}