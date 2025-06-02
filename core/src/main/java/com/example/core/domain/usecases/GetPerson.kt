package com.example.core.domain.usecases

import com.example.core.domain.repositories.PersonRepository
import com.example.network.module.person.Person
import javax.inject.Inject

class GetPerson @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun getPersonById(personId: Int): Person? {
        return personRepository.getPersonById(personId)
    }

    suspend fun search(q: String, page: Int = 1): List<Person> {
        if (q.length < 3 || page <= 0) {
            return listOf()
        }

        return personRepository.searchPersonByName(q = q, page = page)
    }

    suspend fun getPersonsByFilter(queryParameters: List<Pair<String, String>>): List<Person> {
        return personRepository.getPersonByFilter(queryParameters)
    }
}