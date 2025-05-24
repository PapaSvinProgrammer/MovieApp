package com.example.core.domain.repositories

import com.example.network.module.season.Season

interface SeasonRepository {
    suspend fun getSeasonsByMovie(movieId: Int): List<Season>
}