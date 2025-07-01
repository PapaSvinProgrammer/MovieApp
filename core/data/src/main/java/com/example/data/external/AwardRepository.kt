package com.example.data.external

import com.example.model.person.NominationAward

interface AwardRepository {
    suspend fun getMovieAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>

    suspend fun getPersonAwards(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>
}