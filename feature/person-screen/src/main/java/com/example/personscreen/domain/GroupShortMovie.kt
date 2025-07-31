package com.example.personscreen.domain

import com.example.model.movie.ShortMovie
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GroupShortMovie @Inject constructor(
) : UseCase<List<ShortMovie>, Map<String, List<ShortMovie>>>(Dispatchers.Default) {
    override suspend fun run(params: List<ShortMovie>): Map<String, List<ShortMovie>> {
        return params.groupBy { it.enProfession ?: "" }
    }
}