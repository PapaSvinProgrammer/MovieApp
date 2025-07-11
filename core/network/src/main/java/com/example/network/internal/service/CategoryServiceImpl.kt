package com.example.network.internal.service

import com.example.model.category.ItemName
import com.example.network.external.CategoryService
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.category.ItemNameDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class CategoryServiceImpl @Inject constructor(
    private val client: HttpClient
) : CategoryService {
    override suspend fun getGenres(): Result<List<ItemName>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=genres.name")
        }.map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getMovieTypes(): Result<List<ItemName>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=type")
        }.map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getCountries(): Result<List<ItemName>> {
        return safeCall<List<ItemNameDto>> {
            client.get("v1/movie/possible-values-by-field?field=countries.name")
        }.map { list ->
            list.map { it.toDomain() }
        }
    }
}