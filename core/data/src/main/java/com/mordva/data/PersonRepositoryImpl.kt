package com.mordva.data

import com.mordva.domain.repository.PersonRepository
import com.mordva.model.person.Person
import com.mordva.network.external.PersonService
import javax.inject.Inject

internal class PersonRepositoryImpl @Inject constructor(
    private val service: PersonService
) : PersonRepository {
    override suspend fun getPersonById(personId: Int): Result<Person> {
        return service.getPersonById(personId)
    }

    override suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>> {
        return service.getPersonByFilter(queryParameters)
    }

    override suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<Person>> {
        return service.searchPersonByName(
            q = q,
            page = page
        )
    }
}