package com.example.network.external

import com.example.model.person.NominationAward

interface AwardService {
    suspend fun getPersonAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>

    suspend fun getMovieAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>>
}