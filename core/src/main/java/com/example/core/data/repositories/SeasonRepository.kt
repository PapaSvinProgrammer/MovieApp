package com.example.core.data.repositories

import com.example.network.model.season.Season

interface SeasonRepository {
    suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>>
}