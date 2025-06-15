package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.person.NominationAward

interface AwardRepository {
    suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<NominationAward>, NetworkError>

    suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<NominationAward>, NetworkError>
}