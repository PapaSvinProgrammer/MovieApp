package com.example.data.repositoriesImpl

import com.example.core.data.repositories.AwardRepository
import com.example.network.model.person.NominationAward
import com.example.network.service.AwardService
import javax.inject.Inject

internal class AwardRepositoryImpl @Inject constructor(
    private val service: AwardService
): AwardRepository {
    override suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>{
        return service.getMovieAwardsByFilter(queryParameters)
    }

    override suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>> {
        return service.getPersonAwardsByFilter(queryParameters)
    }
}