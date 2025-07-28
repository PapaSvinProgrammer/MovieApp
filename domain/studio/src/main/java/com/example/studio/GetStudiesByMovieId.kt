package com.example.studio

import com.example.data.external.StudioRepository
import com.example.model.movie.Studio
import com.example.studio.model.StudioParams
import com.example.utils.Constants.MOVIES_ID
import com.example.utils.Constants.PAGE_FIELD
import com.example.utils.Constants.SORT_DESC
import com.example.utils.Constants.SORT_FIELD
import com.example.utils.Constants.SORT_TYPE
import com.example.utils.Constants.TITLE_FIELD
import com.example.utils.UseCase
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