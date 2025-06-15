package com.example.core.domain.repositories

import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.module.person.Person

interface PersonRepository {
    suspend fun getPersonById(personId: Int): Operation<Person, NetworkError>

    suspend fun getPersonByFilter(
        queryParameters: List<Pair<String, String>>
    ): Operation<Docs<Person>, NetworkError>

    suspend fun searchPersonByName(
        q: String,
        page: Int
    ): Operation<Docs<Person>, NetworkError>
}