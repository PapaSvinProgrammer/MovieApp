package com.example.core.data.repositories

import com.example.core.domain.repositories.SeasonRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.season.Season
import com.example.network.service.SeasonService
import javax.inject.Inject

class SeasonRepositoryImpl @Inject constructor(
    private val service: SeasonService
): SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): Operation<Docs<Season>, NetworkError> {
        return service.getSeasonsByMovie(movieId)
    }
}