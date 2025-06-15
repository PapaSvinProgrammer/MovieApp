package com.example.core.data.repositories

import com.example.core.domain.repositories.AwardRepository
import com.example.network.module.image.Docs
import com.example.network.module.person.NominationAward
import javax.inject.Inject

class AwardRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): AwardRepository {
    override suspend fun getMovieAwards(queryParameters: List<Pair<String, String>>): Docs<NominationAward> {
        return try {
            ktorClient.getMovieAwardsByFilter(queryParameters)
        } catch (e: Exception) {
            Docs()
        }
    }

    override suspend fun getPersonAwards(queryParameters: List<Pair<String, String>>): Docs<NominationAward> {
        return try {
            ktorClient.getPersonAwardsByFilter(queryParameters)
        } catch (e: Exception) {
            Docs()
        }
    }
}