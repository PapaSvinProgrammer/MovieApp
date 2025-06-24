package com.example.core.data.repositoriesImpl

import com.example.core.data.repositories.SeasonRepository
import com.example.network.model.season.Season
import com.example.network.service.SeasonService
import javax.inject.Inject

internal class SeasonRepositoryImpl @Inject constructor(
    private val service: SeasonService
): SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>> {
        return service.getSeasonsByMovie(movieId)
    }
}