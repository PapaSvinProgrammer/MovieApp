package com.mordva.domain.repository

import com.mordva.model.person.NominationAward

interface AwardRepository {
    suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>

    suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>
}