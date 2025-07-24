package com.example.search.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.collectionusecase.GetCollectionByCategory
import com.example.utils.Constants
import com.example.model.SearchItem
import com.example.movieScreen.GetMovieByFilter
import com.example.movieScreen.SearchMovie
import com.example.person.GetPersonByFilter
import com.example.person.SearchPerson
import com.example.searchhistory.DeleteSearchHistory
import com.example.searchhistory.GetSearchHistory
import com.example.searchhistory.InsertSearchHistory
import com.example.ui.uiState.CollectionUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import com.example.ui.uiState.SearchUIState
import com.example.utils.convert.toHistory
import com.example.utils.convert.toSearchItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SearchViewModel @Inject constructor(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getPersonByFilter: GetPersonByFilter,
    private val searchPerson: SearchPerson,
    private val getMovieByFilter: GetMovieByFilter,
    private val searchMovie: SearchMovie,
    private val insertSearchHistory: InsertSearchHistory,
    private val deleteSearchHistory: DeleteSearchHistory,
    getSearchHistory: GetSearchHistory
): ViewModel() {
    private val _query = MutableStateFlow("")
    private val _isExpanded = MutableStateFlow(false)
    private val _collectionsState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)
    private val _personState = MutableStateFlow(PersonUIState.Loading as PersonUIState)
    private val _topSerialsState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    val query: StateFlow<String> = _query
    val isExpanded: StateFlow<Boolean> = _isExpanded
    val collectionsState: StateFlow<CollectionUIState> = _collectionsState
    val personState: StateFlow<PersonUIState> = _personState
    val topSerialsState: StateFlow<MovieUIState> = _topSerialsState

    private var searchPage = 1
    private val _selectedSearchIndex = MutableStateFlow(0)
    private val _searchState = MutableStateFlow(SearchUIState.Error as SearchUIState)
    val selectedSearchIndex: StateFlow<Int> = _selectedSearchIndex
    val searchState: StateFlow<SearchUIState> = _searchState
    val resultHistory = getSearchHistory.execute().cachedIn(viewModelScope)

    fun updateQuery(text: String) {
        _query.value = text
    }

    fun updateExpanded(state: Boolean) {
        _isExpanded.value = state
    }

    fun updateSelectedSearchIndex(index: Int) {
        _selectedSearchIndex.value = index
    }

    fun getCollections() {
        if (collectionsState.value is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getCollectionByCategory.execute("Фильмы")

            res.onSuccess {
                _collectionsState.value = CollectionUIState.Success(it)
            }
        }
    }

    fun getActorByPopularityMovies() {
        if (personState.value is PersonUIState.Success) return

        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.MOVIES_RATING_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonByFilter.execute(queryParameters)

            res.onSuccess {
                _personState.value = PersonUIState.Success(it)
            }
        }
    }

    fun getTopSerials() {
        if (topSerialsState.value is MovieUIState.Success) return

        val queryParameters = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovieByFilter.execute(queryParameters)

            res.onSuccess {
                _topSerialsState.value = MovieUIState.Success(it)
            }
        }
    }

    fun search(q: String) {
        when (selectedSearchIndex.value) {
            0 -> getMovieByName(q)
            1 -> getPersonByName(q)
        }
    }

    fun loadMore() {
        when (selectedSearchIndex.value) {
            0 -> loadMoreMovieByName()
            1 -> loadMorePersonByName()
        }
    }

    private fun getMovieByName(q: String) {
        searchPage = 1
        _searchState.value = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchMovie.search(q)

            res.onSuccess {
                _searchState.value = SearchUIState.Success(it.toSearchItemList())
            }
        }
    }

    private fun loadMoreMovieByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchMovie.search(
                q = query.value,
                page = searchPage
            )

            res.onSuccess {
                val temp = (searchState.value as SearchUIState.Success)
                    .data
                    .toMutableList()
                temp.addAll(it.toSearchItemList())
                _searchState.value = SearchUIState.Success(temp)
            }
        }
    }

    private fun getPersonByName(q: String) {
        searchPage = 1
        _searchState.value = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchPerson.search(q)

            res.onSuccess {
                _searchState.value = SearchUIState.Success(it.toSearchItemList())
            }
        }
    }

    private fun loadMorePersonByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchPerson.search(
                q = query.value,
                page = searchPage
            )

            res.onSuccess {
                val temp = (searchState.value as SearchUIState.Success)
                    .data
                    .toMutableList()
                temp.addAll(it.toSearchItemList())
                _searchState.value = SearchUIState.Success(temp)
            }
        }
    }

    fun clearSearchResult() {
        _searchState.value = SearchUIState.Error
    }

    fun insertSearchHistoryItem(searchItem: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
           insertSearchHistory.execute(searchItem.toHistory())
        }
    }

    fun deleteSearchHistoryItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSearchHistory.execute(id)
        }
    }
}