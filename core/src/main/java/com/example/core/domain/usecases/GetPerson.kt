package com.example.core.domain.usecases

import com.example.core.domain.repositories.PersonRepository
import com.example.network.Constants
import com.example.network.Constants.AGE_FIELD
import com.example.network.Constants.BIRTHDAY_FIELD
import com.example.network.Constants.COUNT_AWARDS_FIELD
import com.example.network.Constants.PAGE_FIELD
import com.example.network.Constants.PERSON_ID_FIELD
import com.example.network.Constants.SORT_DESC
import com.example.network.Constants.SORT_FIELD
import com.example.network.Constants.SORT_TYPE
import com.example.network.module.person.Person
import javax.inject.Inject

class GetPerson @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun getPersonById(personId: Int): Person? {
        return personRepository.getPersonById(personId)
    }

    suspend fun search(q: String, page: Int = 1): List<Person> {
        if (q.length < 3 || page <= 0) {
            return listOf()
        }

        return personRepository.searchPersonByName(q = q, page = page)
    }

    suspend fun getPersonsByCountAwards(personId: Int, page: Int = 1): List<Person> {
        val queryParameters = mapOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to COUNT_AWARDS_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return personRepository.getPersonByFilter(queryParameters)
    }

    suspend fun getPersonsByBirthday(personId: Int, page: Int = 1): List<Person> {
        val queryParameters = mapOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to BIRTHDAY_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return personRepository.getPersonByFilter(queryParameters)
    }

    suspend fun getPersonByAge(personId: Int, page: Int = 1): List<Person> {
        val queryParameters = mapOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to AGE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return personRepository.getPersonByFilter(queryParameters)
    }
}