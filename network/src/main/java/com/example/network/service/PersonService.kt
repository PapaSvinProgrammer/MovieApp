package com.example.network.service

import com.example.network.core.LIMIT_API_COUNT
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.module.image.Docs
import com.example.network.module.person.Person
import com.example.network.utils.Constants.LIMIT_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.QUERY_FIELD
import com.example.network.utils.Constants.SELECT_FILED
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import jakarta.inject.Inject

class PersonService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPersonById(personId: Int): Operation<Person, NetworkError> {
        return safeCall {
            client.get("v1.4/person/$personId")
        }
    }

    suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Operation<Docs<Person>, NetworkError> {
        return safeCall {
            client.get("v1.4/person/search") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    parameters.append(QUERY_FIELD, q)
                    parameters.append(PAGE_FIELD, page.toString())
                }
            }
        }
    }

    suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Person>, NetworkError> {
        val selectsList = listOf("id", "name", "enName", "photo", "sex", "birthday", "age")

        return safeCall {
            client.get("v1.4/person") {
                url {
                    parameters.append(LIMIT_FIELD, LIMIT_API_COUNT)
                    selectsList.forEach { parameters.append(SELECT_FILED, it) }
                    queryParameters.forEach { parameters.append(it.first, it.second) }
                }
            }
        }
    }
}