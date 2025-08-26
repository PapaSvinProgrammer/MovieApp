package com.mordva.awardlist.domain

import com.mordva.awardlist.presentation.widget.bottomSheet.AwardsFilterType
import com.mordva.awardlist.domain.model.RequestParams
import com.mordva.domain.repository.AwardRepository
import com.mordva.model.person.NominationAward
import com.mordva.util.Constants.MOVIE_ID_FIELD
import com.mordva.util.Constants.NOM_AWARD_TITLE_FIELD
import com.mordva.util.Constants.NOM_AWARD_YEAR_FIELD
import com.mordva.util.Constants.PERSON_ID_FIELD
import com.mordva.util.Constants.SORT_ASC
import com.mordva.util.Constants.SORT_DESC
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GetAwards @Inject constructor(
    private val awardRepository: AwardRepository
) : UseCase<RequestParams, Result<List<NominationAward>>>(Dispatchers.IO) {
    override suspend fun run(params: RequestParams): Result<List<NominationAward>> {
        return when (params.filterType) {
            AwardsFilterType.BY_TITLE -> getAwardsByTitle(
                id = params.id,
                isMovie = params.isMovie
            )

            AwardsFilterType.BY_DATE -> getAwardsByDate(
                id = params.id,
                isMovie = params.isMovie
            )
        }
    }

    private suspend fun getAwardsByTitle(
        id: Int,
        isMovie: Boolean
    ): Result<List<NominationAward>> {
        val queryMovie = listOf(
            MOVIE_ID_FIELD to id.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        val queryPerson = listOf(
            PERSON_ID_FIELD to id.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        val res = if (isMovie) {
            awardRepository.getMovieAwards(queryMovie)
        } else {
            awardRepository.getPersonAwards(queryPerson)
        }

        return res
    }

    private suspend fun getAwardsByDate(
        id: Int,
        isMovie: Boolean
    ): Result<List<NominationAward>> {
        val queryMovie = listOf(
            MOVIE_ID_FIELD to id.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        val queryPerson = listOf(
            PERSON_ID_FIELD to id.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        val res = if (isMovie) {
            awardRepository.getMovieAwards(queryMovie)
        } else {
            awardRepository.getPersonAwards(queryPerson)
        }

        return res
    }
}