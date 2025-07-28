package com.example.awards

import com.example.awards.model.AwardParams
import com.example.data.external.AwardRepository
import com.example.model.person.NominationAward
import com.example.utils.Constants.MOVIE_ID_FIELD
import com.example.utils.Constants.NOM_AWARD_YEAR_FIELD
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.SORT_DESC
import com.example.utils.Constants.SORT_FIELD
import com.example.utils.Constants.SORT_TYPE
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetMovieAwardsByDate @Inject constructor(
    private val awardRepository: AwardRepository
) : UseCase<AwardParams, Result<List<NominationAward>>>(Dispatchers.IO) {
    override suspend fun run(params: AwardParams): Result<List<NominationAward>> {
        val queryParameters = listOf(
            MOVIE_ID_FIELD to params.id.toString(),
            PAGE_FIELD to params.page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getMovieAwards(queryParameters)
    }
}