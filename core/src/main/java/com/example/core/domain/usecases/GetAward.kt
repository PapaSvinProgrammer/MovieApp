package com.example.core.domain.usecases

import com.example.core.domain.repositories.AwardRepository
import com.example.network.Constants.DATE_FIELD
import com.example.network.Constants.MOVIE_ID_FIELD
import com.example.network.Constants.NOM_AWARD_TITLE_FIELD
import com.example.network.Constants.NOM_AWARD_YEAR_FIELD
import com.example.network.Constants.PAGE_FIELD
import com.example.network.Constants.PERSON_ID_FIELD
import com.example.network.Constants.SORT_ASC
import com.example.network.Constants.SORT_DESC
import com.example.network.Constants.SORT_FIELD
import com.example.network.Constants.SORT_TYPE
import com.example.network.module.person.NominationAward
import javax.inject.Inject

class GetAward @Inject constructor(
    private val awardRepository: AwardRepository
) {
    suspend fun getPersonAwardsByDate(personId: Int, page: Int = 1): List<NominationAward> {
        val queryParameters = mapOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }

    suspend fun getPersonAwardsByTitle(personId: Int, page: Int = 1): List<NominationAward> {
        val queryParameters = mapOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }

    suspend fun getMovieAwardsByDate(movieId: Int, page: Int = 1): List<NominationAward> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }

    suspend fun getMovieAwardsByTitle(movieId: Int, page: Int = 1): List<NominationAward> {
        val queryParameters = mapOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }
}