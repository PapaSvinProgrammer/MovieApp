package com.example.studio

import com.example.common.Constants.MOVIES_ID
import com.example.common.Constants.PAGE_FIELD
import com.example.common.Constants.SORT_DESC
import com.example.common.Constants.SORT_FIELD
import com.example.common.Constants.SORT_TYPE
import com.example.common.Constants.TITLE_FIELD
import com.example.data.external.StudioRepository
import com.example.model.movie.Studio
import javax.inject.Inject

class GetStudiesByMovieId @Inject constructor(
    private val studioRepository: StudioRepository
) {
    suspend fun execute(
        movieId: Int,
        page: Int = 1
    ): Result<List<Studio>> {
        val queryParameters = listOf(
            MOVIES_ID to movieId.toString(),
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}