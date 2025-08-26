package com.mordva.network.internal.service

import com.mordva.model.movie.Comment
import com.mordva.network.internal.model.movie.CommentDto
import com.mordva.network.external.CommentService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.internal.mapper.toDomain
import com.mordva.network.internal.model.image.Docs
import com.mordva.util.Constants.LIMIT_FIELD
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class CommentServiceImpl @Inject constructor(
    private val client: HttpClient
) : CommentService {
    override suspend fun getCommentsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Comment>> {
        return safeCall<Docs<CommentDto>> {
            client.get("v1.4/review") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }
}