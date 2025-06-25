package com.example.person

import com.example.core.data.repositories.PersonRepository
import com.example.network.core.ClientException
import com.example.network.model.person.Person
import javax.inject.Inject

class SearchPerson @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun search(q: String, page: Int = 1): Result<List<Person>>{
        if (q.length < 3 || page <= 0) {
            return Result.failure(ClientException())
        }

        return personRepository.searchPersonByName(
            q = q,
            page = page
        )
    }
}