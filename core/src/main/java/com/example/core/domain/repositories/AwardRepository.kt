package com.example.core.domain.repositories

import com.example.network.module.image.Docs
import com.example.network.module.person.NominationAwardPerson

interface AwardRepository {
    suspend fun getMovieAwards(queryParameters: List<Pair<String, String>>): Docs<NominationAwardPerson>
    suspend fun getPersonAwards(queryParameters: List<Pair<String, String>>): Docs<NominationAwardPerson>
}