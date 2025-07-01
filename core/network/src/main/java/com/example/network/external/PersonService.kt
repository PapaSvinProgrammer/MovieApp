package com.example.network.external

import com.example.model.person.Person

interface PersonService {
    suspend fun getPersonById(personId: Int): Result<Person>

    suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Result<List<Person>>

    suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Result<List<Person>>
}