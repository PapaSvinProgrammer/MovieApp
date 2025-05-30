package com.example.core.domain.usecases

import com.example.core.domain.repositories.PersonRepository
import com.example.network.utils.Constants.COUNT_AWARDS_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.SORT_DESC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import com.example.network.module.person.Person
import com.example.network.utils.Constants.MOVIES_RATING_FIELD
import com.example.network.utils.Constants.NOT_NULL_FILED
import com.example.network.utils.Constants.PHOTO_FIELD
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

    suspend fun getPersonsByCountAwards(page: Int = 1): List<Person> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString(),
            SORT_FIELD to COUNT_AWARDS_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return personRepository.getPersonByFilter(queryParameters)
    }

    suspend fun getPersonsByRatingMovies(page: Int = 1): List<Person> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString(),
            SORT_FIELD to MOVIES_RATING_FIELD,
            SORT_TYPE to SORT_DESC,
            NOT_NULL_FILED to PHOTO_FIELD
        )

        return personRepository.getPersonByFilter(queryParameters)
    }

    suspend fun getPersonsByFilter(queryParameters: Map<String, String>): List<Person> {
        return personRepository.getPersonByFilter(queryParameters)
    }
}