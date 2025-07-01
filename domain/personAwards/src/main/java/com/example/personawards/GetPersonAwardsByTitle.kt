package com.example.personawards

import com.example.common.Constants.NOM_AWARD_TITLE_FIELD
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.PERSON_ID_FIELD
import com.example.common.Constants.SORT_ASC
import com.example.common.Constants.SORT_FIELD
import com.example.common.Constants.SORT_TYPE
import com.example.data.external.AwardRepository
import com.example.model.person.NominationAward
import javax.inject.Inject

class GetPersonAwardsByTitle @Inject constructor(
    private val awardRepository: AwardRepository
) {
    suspend fun execute(
        personId: Int,
        page: Int = 1
    ): Result<List<NominationAward>> {
        val queryParameters = listOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_TITLE_FIELD,
            SORT_TYPE to SORT_ASC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }
}