package com.example.awards

import com.example.data.external.AwardRepository
import com.example.model.person.NominationAward
import com.example.utils.Constants.MOVIE_ID_FIELD
import com.example.utils.Constants.NOM_AWARD_TITLE_FIELD
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.SORT_ASC
import com.example.utils.Constants.SORT_FIELD
import com.example.utils.Constants.SORT_TYPE
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