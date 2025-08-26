package com.mordva.data

import com.mordva.domain.repository.AwardRepository
import com.mordva.model.person.NominationAward
import com.mordva.network.external.AwardService
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