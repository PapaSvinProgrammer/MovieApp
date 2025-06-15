package com.example.core.data.repositories

import com.example.core.domain.repositories.AwardRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.person.NominationAward
import com.example.network.service.AwardService
import javax.inject.Inject

class AwardRepositoryImpl @Inject constructor(
    private val service: AwardService
): AwardRepository {
    override suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<NominationAward>, NetworkError> {
        return service.getMovieAwardsByFilter(queryParameters)
    }

    override suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<NominationAward>, NetworkError> {
        return service.getPersonAwardsByFilter(queryParameters)
    }
}