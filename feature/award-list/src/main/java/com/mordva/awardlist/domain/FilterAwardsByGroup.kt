package com.mordva.awardlist.domain

import com.mordva.model.person.NominationAward
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class FilterAwardsByGroup @Inject constructor(
) : UseCase<List<NominationAward>, List<Pair<String, List<NominationAward>>>>(Dispatchers.Default) {
    override suspend fun run(params: List<NominationAward>): List<Pair<String, List<NominationAward>>> {
        return params.groupBy { award ->
            award.nomination?.award?.title + ", " + award.nomination?.award?.year
        }.toList()
    }
}
