package com.mordva.domain.usecase.awards

import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.usecase.awards.model.AwardParams
import com.mordva.model.person.NominationAward
import com.mordva.util.Constants.NOM_AWARD_YEAR_FIELD
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.PERSON_ID_FIELD
import com.mordva.util.Constants.SORT_DESC
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPersonAwardsByDate @Inject constructor(
    private val awardRepository: AwardRepository
) : UseCase<AwardParams, Result<List<NominationAward>>>(Dispatchers.IO) {
    override suspend fun run(params: AwardParams): Result<List<NominationAward>> {
        val queryParameters = listOf(
            PERSON_ID_FIELD to params.id.toString(),
            PAGE_FIELD to params.page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }
}
