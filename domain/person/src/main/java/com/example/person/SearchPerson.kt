package com.example.person

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import com.example.utils.error.ClientException
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