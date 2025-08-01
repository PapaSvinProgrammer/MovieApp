package com.example.movieScreen.domain

import com.example.data.external.PersonRepository
import com.example.model.person.Person
import com.example.utils.Constants
import com.example.utils.UseCase
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
