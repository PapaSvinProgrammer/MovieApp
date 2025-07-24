package com.example.awardlist.domain

import com.example.model.person.NominationAward
import javax.inject.Inject

internal class FilterAwardsByGroup @Inject constructor() {
    fun execute(list: List<NominationAward>): List<Pair<String, List<NominationAward>>> {
        return list.groupBy { award ->
            award.nomination?.award?.title + ", " + award.nomination?.award?.year
        }.toList()
    }
}