package com.example.person

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import javax.inject.Inject

class GetPersonById @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun execute(personId: Int): Result<Person> {
        return personRepository.getPersonById(personId)
    }
}