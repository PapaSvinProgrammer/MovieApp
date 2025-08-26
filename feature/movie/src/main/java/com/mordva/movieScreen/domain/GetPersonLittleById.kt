package com.mordva.movieScreen.domain

import com.mordva.domain.repository.PersonRepository
import com.mordva.model.person.Person
import com.mordva.util.Constants
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetPersonLittleById @Inject constructor(
    private val personRepository: PersonRepository
) : UseCase<Int, Result<Person>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Person> {
        val queryParameters = listOf(
            Constants.ID_FIELD to params.toString()
        )

        return personRepository
            .getPersonByFilter(queryParameters)
            .map { it.first() }
    }
}
