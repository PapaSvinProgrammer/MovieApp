package com.example.core.data.repositories

import com.example.core.domain.repositories.PersonRepository
import com.example.network.module.person.Person
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val ktorClient: KtorClient
): PersonRepository {
    override suspend fun getPersonById(personId: Int): Person? {
        return try {
            ktorClient.getPersonById(personId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getPersonByFilter(queryParameters: List<Pair<String, String>>): List<Person> {
        return try {
            ktorClient.getPersonByFilter(queryParameters)
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun searchPersonByName(q: String, page: Int): List<Person> {
        return try {
            ktorClient.searchPersonByName(
                q = q,
                page = page
            )
        } catch (e: Exception) {
            listOf()
        }
    }
}