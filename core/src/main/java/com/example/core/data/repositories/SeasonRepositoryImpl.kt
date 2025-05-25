package com.example.core.data.repositories

import com.example.core.domain.repositories.SeasonRepository
import com.example.network.KtorClient
import com.example.network.module.season.Season
import javax.inject.Inject

class SeasonRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): SeasonRepository {
    override suspend fun getSeasonsByMovie(movieId: Int): List<Season> {
        return try {
            ktorClient.getSeasonsByMovie(movieId)
        } catch (e: Exception) {
            listOf()
        }
    }
}