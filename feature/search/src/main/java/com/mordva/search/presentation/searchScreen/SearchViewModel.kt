package com.mordva.search.presentation.searchScreen

import androidx.lifecycle.ViewModel
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.usecase.collection.GetCollectionByCategory
import com.mordva.domain.usecase.collection.model.CollectionParams
import com.mordva.domain.usecase.movie.GetMovieByFilter
import com.mordva.domain.usecase.person.GetPersonByFilter
import com.mordva.model.SearchItem
import com.mordva.search.domain.LoadMoreByName
import com.mordva.search.domain.SearchByName
import com.mordva.search.domain.model.RequestParams
import com.mordva.search.presentation.searchScreen.util.toHistory
import com.mordva.search.presentation.searchScreen.widget.UiState
import com.mordva.ui.uiState.CollectionUIState
import com.mordva.ui.uiState.MovieUIState
import com.mordva.ui.uiState.PersonUIState
import com.mordva.ui.uiState.SearchUIState
import com.mordva.util.Constants
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class SearchViewModel @Inject constructor(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getPersonByFilter: GetPersonByFilter,
    private val getMovieByFilter: GetMovieByFilter,
    private val searchByName: SearchByName,
    private val loadMoreByName: LoadMoreByName,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateQuery(text: String) {
        _uiState.update { it.copy(query = text) }
    }

    fun updateExpanded(state: Boolean) {
        _uiState.update {
            it.copy(isExpanded = state)
        }
    }

    fun updateSelectedSearchIndex(index: Int) {
        _uiState.update {
            it.copy(selectedSearchIndex = index)
        }
    }

    fun getCollections() = launchWithoutOld(GET_COLLECTIONS_JOB) {
        if (uiState.value.collectionsState is CollectionUIState.Success) return@launchWithoutOld

        val params = CollectionParams(category = "Фильмы")

        val res = getCollectionByCategory.execute(params)

        res.onSuccess { collections ->
            _uiState.update {
                it.copy(collectionsState = CollectionUIState.Success(collections))
            }
        }
    }

    fun getActorByPopularityMovies() = launchWithoutOld(GET_ACTORS_JOB) {
        if (uiState.value.personState is PersonUIState.Success) return@launchWithoutOld

        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.MOVIES_RATING_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        val res = getPersonByFilter.execute(queryParameters)

        res.onSuccess { actors ->
            _uiState.update {
                it.copy(personState = PersonUIState.Success(actors))
            }
        }
    }

    fun getTopSerials() = launchWithoutOld(GET_SERIALS_JOB) {
        if (uiState.value.topSerialsState is MovieUIState.Success) return@launchWithoutOld

        val queryParameters = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        val res = getMovieByFilter.execute(queryParameters)

        res.onSuccess { serials ->
            _uiState.update {
                it.copy(topSerialsState = MovieUIState.Success(serials))
            }
        }
    }

    fun search() = launchWithoutOld(SEARCH_JOB) {
        _uiState.update {
            it.copy(
                searchState = SearchUIState.Loading,
                page = 1
            )
        }

        val params = RequestParams(
            selectedIndex = uiState.value.selectedSearchIndex,
            q = uiState.value.query,
            page = uiState.value.page
        )

        searchByName.execute(params).onSuccess { items ->
            _uiState.update {
                it.copy(searchState = SearchUIState.Success(items))
            }
        }
    }

    fun loadMore() = launchWithoutOld(SEARCH_JOB) {
        _uiState.update { it.copy(page = it.page + 1) }

        val params = RequestParams(
            selectedIndex = uiState.value.selectedSearchIndex,
            q = uiState.value.query,
            page = uiState.value.page
        )

        loadMoreByName.execute(params).onSuccess { items ->
            val temp = (uiState.value.searchState as SearchUIState.Success)
                .data
                .toMutableList()

            temp.addAll(items)

            _uiState.update {
                it.copy(searchState = SearchUIState.Success(temp))
            }
        }
    }

    fun clearSearchResult() {
        _uiState.update {
            it.copy(searchState = SearchUIState.Error)
        }
    }

    fun insertSearchHistoryItem(searchItem: SearchItem) = launchWithoutOld(INSERT_HISTORY_JOB) {
        historyRepository.insert(searchItem.toHistory())
    }

    fun deleteSearchHistoryItem(id: Int) = launchWithoutOld(DELETE_HISTORY_JOB) {
        historyRepository.delete(id)
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_COLLECTIONS_JOB = "get_collections"
        const val GET_ACTORS_JOB = "get_actors_by_popularity"
        const val GET_SERIALS_JOB = "get_top_serials"
        const val INSERT_HISTORY_JOB = "insert_search_history"
        const val DELETE_HISTORY_JOB = "delete_search_history"
        const val SEARCH_JOB = "search_movies_and_persons"
    }
}
