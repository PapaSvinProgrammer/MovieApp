package com.example.network.internal.service

import com.example.common.Constants.LIMIT_FIELD
import com.example.network.internal.model.image.Docs
import com.example.model.person.NominationAward
import com.example.network.internal.model.person.NominationAwardDto
import com.example.network.external.AwardService
import com.example.network.internal.core.LIMIT_API_COUNT
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class AwardServiceImpl @Inject constructor(
    private val client: HttpClient
) : AwardService {
    override suspend fun getPersonAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>> {
        return safeCall<Docs<NominationAwardDto>> {
            client.get("v1.4/person/awards") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getMovieAwardsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<NominationAward>> {
        return safeCall<Docs<NominationAwardDto>> {
            client.get("v1.4/movie/awards") {
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