package com.example.core.data.repositories

import com.example.core.domain.repositories.StudioRepository
import com.example.network.KtorClient
import com.example.network.module.movie.Studio
import javax.inject.Inject

class StudioRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): StudioRepository {
    override suspend fun getStudies(queryParameters: List<Pair<String, String>>): List<Studio> {
        return try {
            ktorClient.getStudies(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }
}