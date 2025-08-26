package com.mordva.data

import com.mordva.domain.repository.SeasonRepository
import com.mordva.model.season.Season
import com.mordva.network.external.SeasonService
import javax.inject.Inject

internal class SeasonRepositoryImpl @Inject constructor(
    private val service: SeasonService
): SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): Result<List<Season>> {
        return service.getSeasonsByMovie(movieId)
    }
}