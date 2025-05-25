package com.example.core.domain.repositories

import com.example.network.module.person.Person

interface PersonRepository {
    suspend fun getPersonById(personId: Int): Person?
    suspend fun getPersonByFilter(queryParameters: Map<String, String>): List<Person>
    suspend fun searchPersonByName(q: String, page: Int): List<Person>
}