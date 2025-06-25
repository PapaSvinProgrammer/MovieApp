package com.example.personawards

import com.example.core.data.repositories.AwardRepository
import com.example.network.model.person.NominationAward
import com.example.network.utils.Constants.NOM_AWARD_YEAR_FIELD
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.PERSON_ID_FIELD
import com.example.network.utils.Constants.SORT_DESC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
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