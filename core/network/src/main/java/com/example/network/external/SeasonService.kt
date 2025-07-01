package com.example.network.external

import com.example.model.season.Season

interface SeasonService {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>>
}