package com.mordva.movieScreen.domain

import com.mordva.model.person.PersonMovie
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class FilterPersonsLikeVoiceActors @Inject constructor(
) : UseCase<List<PersonMovie>, List<PersonMovie>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovie>): List<PersonMovie> {
        return params.filter { it.enProfession == "voice_actor" }
    }
}
