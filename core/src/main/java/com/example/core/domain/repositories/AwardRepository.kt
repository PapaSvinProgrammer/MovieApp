package com.example.core.domain.repositories

import com.example.network.module.person.NominationAward

interface AwardRepository {
    suspend fun getMovieAwards(queryParameters: Map<String, String>): List<NominationAward>
    suspend fun getPersonAwards(queryParameters: Map<String, String>): List<NominationAward>
}