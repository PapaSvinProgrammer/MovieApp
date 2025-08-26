package com.mordva.network.external

import com.mordva.model.season.Season

interface SeasonService {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>>
}