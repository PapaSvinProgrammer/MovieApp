package com.mordva.domain.usecase.studio

import com.mordva.domain.repository.StudioRepository
import com.mordva.model.movie.Studio
import com.mordva.util.Constants.PAGE_FIELD
import com.mordva.util.Constants.SORT_DESC
import com.mordva.util.Constants.SORT_FIELD
import com.mordva.util.Constants.SORT_TYPE
import com.mordva.util.Constants.TITLE_FIELD
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetStudiesByTitle @Inject constructor(
    private val studioRepository: StudioRepository
) : UseCase<Int, Result<List<Studio>>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<List<Studio>> {
        val queryParameters = listOf(
            PAGE_FIELD to params.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}