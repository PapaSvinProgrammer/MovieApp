package com.example.core.domain.usecases

import com.example.core.domain.repositories.PersonRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.person.Person
import kotlinx.coroutines.async
import javax.inject.Inject

class GetPerson @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun getPersonById(personId: Int): Operation<Person, NetworkError> {
        return personRepository.getPersonById(personId)
    }

    suspend fun search(q: String, page: Int = 1): Operation<Docs<Person>, NetworkError>{
        if (q.length < 3 || page <= 0) {
            return Operation.Error(NetworkError.CLIENT_ERROR)
        }

        return personRepository.searchPersonByName(q = q, page = page)
    }

    suspend fun getPersonsByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Person>, NetworkError> {
        return personRepository.getPersonByFilter(queryParameters)
    }
}