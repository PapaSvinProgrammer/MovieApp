package com.mordva.network.internal.service

import com.mordva.model.image.MovieImageParams
import com.mordva.model.image.Poster
import com.mordva.network.external.ImageService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.internal.mapper.toDomain
import com.mordva.network.internal.model.image.Docs
import com.mordva.network.internal.model.image.PosterDto
import com.mordva.network.internal.util.toKtorString
import com.mordva.util.Constants.LIMIT_FIELD
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.TYPE_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class ImageServiceImpl @Inject constructor(
    private val client: HttpClient
) : ImageService {
    override suspend fun getMovieImages(movieId: Int, page: Int): Result<List<Poster>> {
        return safeCall<Docs<PosterDto>> {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, page.toString())
                    parameters.append(MOVIE_ID_FIELD, movieId.toString())
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getMoviesImagesByType(params: MovieImageParams): Result<List<Poster>> {
        return safeCall<Docs<PosterDto>> {
            client.get("v1.4/image") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(PAGE_FIELD, params.page.toString())
                    parameters.append(MOVIE_ID_FIELD, params.movieId.toString())
                    params.types.forEach { parameters.append(TYPE_FIELD, it.toKtorString()) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }
}