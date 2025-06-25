package com.example.personawards

import com.example.core.data.repositories.AwardRepository
import com.example.network.model.person.NominationAward
import com.example.network.utils.Constants.MOVIE_ID_FIELD
import com.example.network.utils.Constants.NOM_AWARD_TITLE_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.SORT_ASC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import javax.inject.Inject

class GetMovieAwardsByTitle @Inject constructor(
    private val awardRepository: AwardRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<NominationAward>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }
}