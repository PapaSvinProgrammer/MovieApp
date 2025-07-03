package com.example.network.internal.service

import com.example.common.Constants.LIMIT_FIELD
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.QUERY_FIELD
import com.example.common.Constants.SELECT_FILED
import com.example.model.person.Person
import com.example.network.internal.model.person.PersonDto
import com.example.network.external.PersonService
import com.example.network.internal.core.LIMIT_API_COUNT
import com.example.network.internal.core.safeCall
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.model.image.Docs
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class PersonServiceImpl @Inject constructor(
    private val client: HttpClient
) : PersonService {
    override suspend fun getPersonById(personId: Int): Result<Person> {
        return safeCall<PersonDto>{
            client.get("v1.4/person/$personId")
        }.map { it.toDomain() }
    }

    override suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<Person>> {
        return safeCall<Docs<PersonDto>> {
            client.get("v1.4/person/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(QUERY_FIELD, q)
                    parameters.append(PAGE_FIELD, page.toString())
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }

    override suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>> {
        val selectsList = listOf("id", "name", "enName", "photo", "sex", "birthday", "age")

        return safeCall<Docs<PersonDto>> {
            client.get("v1.4/person") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    selectsList.forEach { parameters.append(SELECT_FILED, it) }
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }.map { doc ->
            doc.docs.map { it.toDomain() }
        }
    }
}