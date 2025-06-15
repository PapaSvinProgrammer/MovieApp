package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.season.Season

interface SeasonRepository {
    suspend fun getSeasonsByMovie(movieId: Int): Operation<Docs<Season>, NetworkError>
}