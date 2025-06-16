package com.example.core.domain.usecases

import com.example.core.domain.repositories.AwardRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.NOM_AWARD_TITLE_FIELD
import com.example.network.utils.Constants.NOM_AWARD_YEAR_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.PERSON_ID_FIELD
import com.example.network.utils.Constants.SORT_ASC
import com.example.network.utils.Constants.SORT_DESC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import com.example.network.module.person.NominationAward
import javax.inject.Inject

class GetAward @Inject constructor(
    private val awardRepository: AwardRepository
) {
    suspend fun getPersonAwardsByDate(
        personId: Int,
        page: Int = 1
    ): Operation<Docs<NominationAward>, NetworkError> {
        val queryParameters = listOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }

    suspend fun getPersonAwardsByTitle(
        personId: Int,
        page: Int = 1
    ): Operation<Docs<NominationAward>, NetworkError> {
        val queryParameters = listOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }

    suspend fun getMovieAwardsByDate(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<NominationAward>, NetworkError> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }

    suspend fun getMovieAwardsByTitle(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<NominationAward>, NetworkError> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }
}