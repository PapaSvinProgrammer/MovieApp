package com.mordva.domain.repository

import com.mordva.model.person.Person

interface PersonRepository {
    suspend fun getPersonById(personId: Int): Result<Person>

    suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>>

    suspend fun searchPersonByName(
        q: String,
        page: Int = 1
    ): Result<List<Person>>
}