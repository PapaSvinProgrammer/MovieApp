package com.example.data.internal

import com.example.data.external.AwardRepository
import com.example.model.person.NominationAward
import com.example.network.external.AwardService
import javax.inject.Inject

internal class AwardRepositoryImpl @Inject constructor(
    private val service: AwardService
) : AwardRepository {
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