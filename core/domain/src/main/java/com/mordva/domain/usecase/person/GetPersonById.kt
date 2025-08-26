package com.mordva.domain.usecase.person

import com.mordva.domain.repository.PersonRepository
import com.mordva.model.person.Person
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPersonById @Inject constructor(
    private val personRepository: PersonRepository
) : UseCase<Int, Result<Person>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Person> {
        return personRepository.getPersonById(params)
    }
}
