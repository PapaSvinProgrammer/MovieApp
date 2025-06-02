package com.example.core.domain.repositories

import com.example.network.module.movie.Studio

interface StudioRepository {
    suspend fun getStudies(queryParameters: List<Pair<String, String>>): List<Studio>
}