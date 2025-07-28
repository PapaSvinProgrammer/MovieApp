package com.example.person

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPersonById @Inject constructor(
    private val personRepository: PersonRepository
) : UseCase<Int, Result<Person>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Person> {
        return personRepository.getPersonById(params)
    }
}
