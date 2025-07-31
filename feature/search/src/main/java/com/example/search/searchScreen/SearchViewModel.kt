package com.example.search.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.collectionusecase.GetCollectionByCategory
import com.example.collectionusecase.model.CollectionParams
import com.example.data.external.HistoryRepository
import com.example.model.SearchItem
import com.example.movieScreen.GetMovieByFilter
import com.example.movieScreen.SearchMovie
import com.example.movieScreen.model.MovieParams
import com.example.person.GetPersonByFilter
import com.example.person.SearchPerson
import com.example.person.model.PersonParams
import com.example.search.searchScreen.widget.UiState
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import com.example.ui.uiState.SearchUIState
import com.example.utils.Constants
import com.example.utils.cancelAllJobs
import com.example.utils.convert.toHistory
import com.example.utils.convert.toSearchItemList
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class SearchViewModel @Inject constructor(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getPersonByFilter: GetPersonByFilter,
    private val searchPerson: SearchPerson,
    private val getMovieByFilter: GetMovieByFilter,
    private val searchMovie: SearchMovie,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private var searchPage = 1
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    val resultHistory = historyRepository.getAll().cachedIn(viewModelScope)

    fun updateQuery(text: String) {
        _uiState.update {
            it.copy(query = text)
        }
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

    fun search(q: String) {
        when (uiState.value.selectedSearchIndex) {
            0 -> getMovieByName(q)
            1 -> getPersonByName(q)
        }
    }

    fun loadMore() {
        when (uiState.value.selectedSearchIndex) {
            0 -> loadMoreMovieByName()
            1 -> loadMorePersonByName()
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

    private fun getMovieByName(q: String) = launchWithoutOld(GET_MOVIES_BY_NAME_JOB) {
        searchPage = 1
        _uiState.update {
            it.copy(searchState = SearchUIState.Loading)
        }

        val params = MovieParams(q = q)

        val res = searchMovie.execute(params)

        res.onSuccess { movies ->
            _uiState.update {
                it.copy(searchState = SearchUIState.Success(movies.toSearchItemList()))
            }
        }
    }

    private fun loadMoreMovieByName() = launchWithoutOld(LOAD_MOVIES_BY_NAME_JOB) {
        searchPage++

        val params = MovieParams(
            q = _uiState.value.query,
            page = searchPage
        )

        val res = searchMovie.execute(params)

        res.onSuccess { movies ->
            val temp = (uiState.value.searchState as SearchUIState.Success)
                .data
                .toMutableList()

            temp.addAll(movies.toSearchItemList())

            _uiState.update {
                it.copy(searchState = SearchUIState.Success(temp))
            }
        }
    }

    private fun getPersonByName(q: String) = launchWithoutOld(GET_PERSONS_BY_NAME_JOB) {
        searchPage = 1
        _uiState.update {
            it.copy(searchState = SearchUIState.Loading)
        }

        val params = PersonParams(
            q = q,
            page = searchPage
        )

        val res = searchPerson.execute(params)

        res.onSuccess { persons ->
            _uiState.update {
                it.copy(searchState = SearchUIState.Success(persons.toSearchItemList()))
            }
        }
    }

    private fun loadMorePersonByName() = launchWithoutOld(LOAD_PERSONS_BY_NAME_JOB) {
        searchPage++

        val params = PersonParams(
            q = uiState.value.query,
            page = searchPage
        )

        val res = searchPerson.execute(params)

        res.onSuccess { persons ->
            val temp = (uiState.value.searchState as SearchUIState.Success)
                .data
                .toMutableList()

            temp.addAll(persons.toSearchItemList())

            _uiState.update {
                it.copy(searchState = SearchUIState.Success(temp))
            }
        }
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
        const val GET_MOVIES_BY_NAME_JOB = "get_movies_by_name"
        const val LOAD_MOVIES_BY_NAME_JOB = "load_more_movies_by_name"
        const val GET_PERSONS_BY_NAME_JOB = "get_persons_by_name"
        const val LOAD_PERSONS_BY_NAME_JOB = "load_more_persons_by_name"
    }
}
