package com.example.core.domain.usecases

import com.example.core.domain.repositories.StudioRepository
import com.example.network.Constants
import com.example.network.Constants.MOVIE_ID_FIELD
import com.example.network.Constants.PAGE_FIELD
import com.example.network.Constants.SORT_ASC
import com.example.network.Constants.SORT_DESC
import com.example.network.Constants.SORT_FIELD
import com.example.network.Constants.SORT_TYPE
import com.example.network.Constants.TITLE_FIELD
import com.example.network.module.movie.Studio
import javax.inject.Inject

class GetStudio @Inject constructor(
    private val studioRepository: StudioRepository
) {
    suspend fun getStudiesByMovieId(movieId: Int, page: Int = 1): List<Studio> {
        val queryParameters = mapOf(
            "movies.id" to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }

    suspend fun getStudiesByTitle(page: Int = 1): List<Studio> {
        val queryParameters = mapOf(
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}