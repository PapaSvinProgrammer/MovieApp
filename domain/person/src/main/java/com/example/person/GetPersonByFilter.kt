package com.example.person

import com.example.core.data.repositories.PersonRepository
import com.example.network.model.person.Person
import javax.inject.Inject

class GetPersonByFilter @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun execute(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>> {
        return personRepository.getPersonByFilter(queryParameters)
    }
}