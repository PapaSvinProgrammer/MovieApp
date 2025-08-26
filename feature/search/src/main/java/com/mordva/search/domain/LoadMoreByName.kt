package com.mordva.search.domain

import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository
import com.mordva.model.SearchItem
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.presentation.searchScreen.util.toSearchItemList
import com.mordva.util.UseCase
import com.mordva.util.error.ClientException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import javax.inject.Inject

internal class LoadMoreByName @Inject constructor(
    private val movieRepository: MovieRepository,
    private val personRepository: PersonRepository
) : UseCase<RequestParams, Result<List<SearchItem>>>(Dispatchers.IO) {
    override suspend fun run(params: RequestParams): Result<List<SearchItem>> {
        return when (params.selectedIndex) {
            0 -> loadMoreMovieByName(params.q, params.page)
            1 -> loadMorePersonByName(params.q, params.page)
            else -> Result.failure(ClientException())
        }
    }

    private suspend fun loadMoreMovieByName(q: String, page: Int): Result<List<SearchItem>> {
        return movieRepository.search(q, page).map { it.toSearchItemList() }
    }

    private suspend fun loadMorePersonByName(q: String, page: Int): Result<List<SearchItem>> {
        return personRepository.searchPersonByName(q, page).map { it.toSearchItemList() }
    }
}
