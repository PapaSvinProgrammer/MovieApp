package com.example.core.data.repositories

import com.example.core.domain.repositories.PersonRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.person.Person
import com.example.network.service.PersonService
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val service: PersonService
): PersonRepository {
    override suspend fun getPersonById(personId: Int): Operation<Person, NetworkError> {
        return service.getPersonById(personId)
    }

    override suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Person>, NetworkError> {
        return service.getPersonByFilter(queryParameters)
    }

    override suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Operation<Docs<Person>, NetworkError> {
        return service.searchPersonByName(
            q = q,
            page = page
        )
    }
}