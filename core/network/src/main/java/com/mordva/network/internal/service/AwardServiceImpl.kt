package com.mordva.network.internal.service

import com.mordva.network.internal.model.image.Docs
import com.mordva.model.person.NominationAward
import com.mordva.network.internal.model.person.NominationAwardDto
import com.mordva.network.external.AwardService
import com.mordva.network.internal.core.LIMIT_API_COUNT
import com.mordva.network.internal.core.safeCall
import com.mordva.network.internal.mapper.toDomain
import com.mordva.util.Constants.LIMIT_FIELD
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