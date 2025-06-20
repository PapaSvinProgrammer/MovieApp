package com.example.core.domain.usecases

import com.example.core.domain.repositories.StudioRepository
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.module.image.Docs
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.SORT_DESC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import com.example.network.utils.Constants.TITLE_FIELD
import com.example.network.module.movie.Studio
import javax.inject.Inject

class GetStudio @Inject constructor(
    private val studioRepository: StudioRepository
) {
    suspend fun getStudiesByMovieId(
        movieId: Int,
        page: Int = 1
    ): Operation<Docs<Studio>, NetworkError> {
        val queryParameters = listOf(
            "movies.id" to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }

    suspend fun getStudiesByTitle(page: Int = 1): Operation<Docs<Studio>, NetworkError> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}