package com.example.awards

import com.example.data.external.AwardRepository
import com.example.model.person.NominationAward
import com.example.utils.Constants.NOM_AWARD_YEAR_FIELD
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.PERSON_ID_FIELD
import com.example.utils.Constants.SORT_DESC
import com.example.utils.Constants.SORT_FIELD
import com.example.utils.Constants.SORT_TYPE
import javax.inject.Inject

class GetPersonAwardsByDate @Inject constructor(
    private val awardRepository: AwardRepository
) {
    suspend fun execute(
        personId: Int,
        page: Int = 1
    ): Result<List<NominationAward>>{
        val queryParameters = listOf(
            PERSON_ID_FIELD to personId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to NOM_AWARD_YEAR_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return awardRepository.getPersonAwards(queryParameters)
    }
}