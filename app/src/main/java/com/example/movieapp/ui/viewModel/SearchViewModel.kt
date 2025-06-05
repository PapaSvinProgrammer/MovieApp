package com.example.movieapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.core.domain.module.SearchItem
import com.example.core.domain.repositories.HistoryRepository
import com.example.core.domain.usecases.GetCollection
import com.example.core.domain.usecases.GetMovie
import com.example.core.domain.usecases.GetPerson
import com.example.core.domain.usecases.GetSearchHistory
import com.example.core.utils.toHistoryEntity
import com.example.core.utils.toSearchItemList
import com.example.movieapp.ui.screen.uiState.CollectionUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.movieapp.ui.screen.uiState.SearchUIState
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getCollection: GetCollection,
    private val getPerson: GetPerson,
    private val getMovie: GetMovie,
    private val historyRepository: HistoryRepository,
    getSearchHistory: GetSearchHistory
): ViewModel() {
    var query by mutableStateOf("")
        private set
    var isExpanded by mutableStateOf(false)
        private set
    var collectionsState by mutableStateOf(CollectionUIState.Loading as CollectionUIState)
        private set
    var dubbingPersonState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set
    var topSerialsState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set

    private var searchPage = 1
    var selectedSearchIndex by mutableIntStateOf(0)
        private set
    var searchState by mutableStateOf(SearchUIState.Error as SearchUIState)
        private set
    val resultHistory = getSearchHistory.execute().cachedIn(viewModelScope)

    fun updateQuery(text: String) {
        query = text
    }

    fun updateExpanded(state: Boolean) {
        isExpanded = state
    }

    fun updateSelectedSearchIndex(index: Int) {
        selectedSearchIndex = index
    }

    fun getCollections() {
        if (collectionsState is CollectionUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getCollection.getByCategory("Фильмы")

            if (res.isNotEmpty()) {
                collectionsState = CollectionUIState.Success(res)
            }
        }
    }

    fun getActorByCountAwards() {
        if (dubbingPersonState is PersonUIState.Success) return

        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.COUNT_AWARDS_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonsByFilter(queryParameters)

            if (res.isNotEmpty()) {
                dubbingPersonState = PersonUIState.Success(res)
            }
        }
    }

    fun getTopSerials() {
        if (topSerialsState is MovieUIState.Success) return

        val queryParameters = listOf(
            Constants.IS_SERIES_FIELD to Constants.TRUE,
            Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.getByFilter(queryParameters)

            if (res.isNotEmpty()) {
                topSerialsState = MovieUIState.Success(res)
            }
        }
    }

    fun search(q: String) {
        when (selectedSearchIndex) {
            0 -> getMovieByName(q)
            1 -> getPersonByName(q)
        }
    }

    fun loadMore() {
        when (selectedSearchIndex) {
            0 -> loadMoreMovieByName()
            1 -> loadMorePersonByName()
        }
    }

    private fun getMovieByName(q: String) {
        searchPage = 1
        searchState = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.search(q).toSearchItemList()

            if (res.isEmpty()) {
                searchState = SearchUIState.Error
            }
            else {
                searchState = SearchUIState.Success(res)
            }
        }
    }

    private fun loadMoreMovieByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.search(query, searchPage).toSearchItemList()
            val temp = (searchState as SearchUIState.Success).data.toMutableList()
            temp.addAll(res)
            searchState = SearchUIState.Success(temp)
        }
    }

    private fun getPersonByName(q: String) {
        searchPage = 1
        searchState = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.search(q).toSearchItemList()

            if (res.isEmpty()) {
                searchState = SearchUIState.Error
            }
            else {
                searchState = SearchUIState.Success(res)
            }
        }
    }

    private fun loadMorePersonByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.search(query, searchPage).toSearchItemList()
            val temp = (searchState as SearchUIState.Success).data.toMutableList()
            temp.addAll(res)
            searchState = SearchUIState.Success(temp)
        }
    }

    fun clearSearchResult() {
        searchState = SearchUIState.Error
    }

    fun insertSearchHistoryItem(searchItem: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.insert(searchItem.toHistoryEntity())
        }
    }

    fun deleteSearchHistoryItem(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.delete(id)
        }
    }
}