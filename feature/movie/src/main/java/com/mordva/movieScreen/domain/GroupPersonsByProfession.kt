package com.mordva.movieScreen.domain

import com.mordva.movieScreen.domain.model.PersonMovieExtended
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GroupPersonsByProfession @Inject constructor(
) : UseCase<List<PersonMovieExtended>, Map<String, List<PersonMovieExtended>>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovieExtended>): Map<String, List<PersonMovieExtended>> {
        return params.groupBy { it.enProfession ?: " " }
    }
}
