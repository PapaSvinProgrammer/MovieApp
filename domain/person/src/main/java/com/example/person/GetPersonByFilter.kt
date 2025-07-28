package com.example.person

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPersonByFilter @Inject constructor(
    private val personRepository: PersonRepository
) : UseCase<List<Pair<String, String>>, Result<List<Person>>>(Dispatchers.IO) {
    override suspend fun run(params: List<Pair<String, String>>): Result<List<Person>> {
        return personRepository.getPersonByFilter(params)
    }
}
