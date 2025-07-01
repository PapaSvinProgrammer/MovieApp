package com.example.data.internal

import com.example.data.external.SeasonRepository
import com.example.model.season.Season
import com.example.network.external.SeasonService
import javax.inject.Inject

internal class SeasonRepositoryImpl @Inject constructor(
    private val service: SeasonService
): SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>> {
        return service.getSeasonsByMovie(movieId)
    }
}