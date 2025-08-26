package com.mordva.domain.usecase.person

import com.mordva.domain.repository.PersonRepository
import com.mordva.domain.usecase.person.model.PersonParams
import com.mordva.model.person.Person
import com.mordva.util.UseCase
import com.mordva.util.error.ClientException
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
