package com.example.studio

import com.example.core.data.repositories.StudioRepository
import com.example.network.model.movie.Studio
import com.example.network.utils.Constants.PAGE_FIELD
import com.example.network.utils.Constants.SORT_DESC
import com.example.network.utils.Constants.SORT_FIELD
import com.example.network.utils.Constants.SORT_TYPE
import com.example.network.utils.Constants.TITLE_FIELD
import javax.inject.Inject

class GetStudiesByTitle @Inject constructor(
    private val studioRepository: StudioRepository
) {
    suspend fun execute(page: Int = 1): Result<List<Studio>> {
        val queryParameters = listOf(
            PAGE_FIELD to page.toString(),
            SORT_FIELD to TITLE_FIELD,
            SORT_TYPE to SORT_DESC
        )

        return studioRepository.getStudies(queryParameters)
    }
}