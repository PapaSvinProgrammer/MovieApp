package com.example.core.domain.person

import com.example.core.data.repositories.PersonRepository
import com.example.network.model.person.Person
import javax.inject.Inject

class GetPersonById @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun getPersonById(personId: Int): Result<Person> {
        return personRepository.getPersonById(personId)
    }
}