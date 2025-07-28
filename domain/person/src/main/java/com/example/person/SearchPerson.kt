package com.example.person

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import com.example.person.model.PersonParams
import com.example.utils.UseCase
import com.example.utils.error.ClientException
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchPerson @Inject constructor(
    private val personRepository: PersonRepository
) : UseCase<PersonParams, Result<List<Person>>>(Dispatchers.IO) {
    override suspend fun run(params: PersonParams): Result<List<Person>> {
        if (params.q.length < 3 || params.page <= 0) {
            return Result.failure(ClientException())
        }

        return personRepository.searchPersonByName(
            q = params.q,
            page = params.page
        )
    }
}
