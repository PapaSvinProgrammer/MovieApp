package com.example.movieapp.viewModels

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
import com.example.movieapp.ui.uiState.CollectionUIState
import com.example.movieapp.ui.uiState.MovieUIState
import com.example.movieapp.ui.uiState.PersonUIState
import com.example.movieapp.ui.uiState.SearchUIState
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
    var personState by mutableStateOf(PersonUIState.Loading as PersonUIState)
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

            res.onSuccess {
                collectionsState = CollectionUIState.Success(it.docs)
            }.onError {

            }
        }
    }

    fun getActorByPopularityMovies() {
        if (personState is PersonUIState.Success) return

        val queryParameters = listOf(
            Constants.SORT_FIELD to Constants.MOVIES_RATING_FIELD,
            Constants.SORT_TYPE to Constants.SORT_DESC
        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonsByFilter(queryParameters)

            res.onSuccess {
                personState = PersonUIState.Success(it.docs)
            }.onError {

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

            res.onSuccess {
                topSerialsState = MovieUIState.Success(it.docs)
            }.onError {

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
            val res = getMovie.search(q)

            res.onSuccess {
                searchState = SearchUIState.Success(it.docs.toSearchItemList())
            }.onError {
                searchState = SearchUIState.Error
            }
        }
    }

    private fun loadMoreMovieByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = getMovie.search(query, searchPage)

            res.onSuccess {
                val temp = (searchState as SearchUIState.Success).data.toMutableList()
                temp.addAll(it.docs.toSearchItemList())
                searchState = SearchUIState.Success(temp)
            }
        }
    }

    private fun getPersonByName(q: String) {
        searchPage = 1
        searchState = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.search(q)

            res.onSuccess {
                searchState = SearchUIState.Success(it.docs.toSearchItemList())
            }.onError {
                searchState = SearchUIState.Error
            }
        }
    }

    private fun loadMorePersonByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.search(query, searchPage)

            res.onSuccess {
                val temp = (searchState as SearchUIState.Success).data.toMutableList()
                temp.addAll(it.docs.toSearchItemList())
                searchState = SearchUIState.Success(temp)
            }
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