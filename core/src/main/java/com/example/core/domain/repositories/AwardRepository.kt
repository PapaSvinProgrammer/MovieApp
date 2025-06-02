package com.example.core.domain.repositories

import com.example.network.module.person.NominationAward

interface AwardRepository {
    suspend fun getMovieAwards(queryParameters: List<Pair<String, String>>): List<NominationAward>
    suspend fun getPersonAwards(queryParameters: List<Pair<String, String>>): List<NominationAward>
}