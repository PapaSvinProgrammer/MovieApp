package com.mordva.domain.usecase.studio

import com.mordva.domain.repository.StudioRepository
import com.mordva.domain.usecase.studio.model.StudioParams
import com.mordva.model.movie.Studio
import com.mordva.util.Constants.MOVIES_ID
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.SORT_DESC
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import com.mordva.util.Constants.TITLE_FIELD
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetStudiesByMovieId @Inject constructor(
    private val studioRepository: StudioRepository
) : UseCase<StudioParams, Result<List<Studio>>>(Dispatchers.IO) {
    override suspend fun run(params: StudioParams): Result<List<Studio>> {
        val queryParameters = listOf(
            MOVIES_ID to params.movieId.toString(),
            PAGE_FIELD to params.page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}