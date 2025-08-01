package com.example.movieScreen.domain

import com.example.movieScreen.domain.model.PersonMovieExtended
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GroupPersonsByProfession @Inject constructor(
) : UseCase<List<PersonMovieExtended>, Map<String, List<PersonMovieExtended>>>(Dispatchers.Default) {
    override suspend fun run(params: List<PersonMovieExtended>): Map<String, List<PersonMovieExtended>> {
        return params.groupBy { it.enProfession ?: " " }
    }
}
