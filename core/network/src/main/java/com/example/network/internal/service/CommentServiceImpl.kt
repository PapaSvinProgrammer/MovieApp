package com.example.network.internal.service

import com.example.model.movie.Comment
import com.example.network.internal.model.movie.CommentDto
import com.example.network.external.CommentService
import com.example.network.internal.core.LIMIT_API_COUNT
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.image.Docs
import com.example.utils.Constants.LIMIT_FIELD
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