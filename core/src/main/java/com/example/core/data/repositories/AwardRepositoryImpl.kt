package com.example.core.data.repositories

import com.example.core.domain.repositories.AwardRepository
import com.example.network.KtorClient
import com.example.network.module.person.NominationAward
import javax.inject.Inject

class AwardRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): AwardRepository {
    override suspend fun getMovieAwards(queryParameters: List<Pair<String, String>>): List<NominationAward> {
        return try {
            ktorClient.getMovieAwardsByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getPersonAwards(queryParameters: List<Pair<String, String>>): List<NominationAward> {
        return try {
            ktorClient.getPersonAwardsByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }
}