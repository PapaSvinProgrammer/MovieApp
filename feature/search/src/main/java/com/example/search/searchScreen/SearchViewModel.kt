package com.example.search.searchScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.collectionusecase.GetCollectionByCategory
import com.example.common.Constants
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
import com.example.utils.toHistory
import com.example.utils.toSearchItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getPersonByFilter: GetPersonByFilter,
    private val searchPerson: SearchPerson,
    private val getMovieByFilter: GetMovieByFilter,
    private val searchMovie: SearchMovie,
    private val insertSearchHistory: InsertSearchHistory,
    private val deleteSearchHistory: DeleteSearchHistory,
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
            val res = getCollectionByCategory.execute("Фильмы")

            res.onSuccess {
                collectionsState = CollectionUIState.Success(it)
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
            val res = getPersonByFilter.execute(queryParameters)

            res.onSuccess {
                personState = PersonUIState.Success(it)
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
            val res = getMovieByFilter.execute(queryParameters)

            res.onSuccess {
                topSerialsState = MovieUIState.Success(it)
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
            val res = searchMovie.search(q)

            res.onSuccess {
                searchState = SearchUIState.Success(it.toSearchItemList())
            }
        }
    }

    private fun loadMoreMovieByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchMovie.search(query, searchPage)

            res.onSuccess {
                val temp = (searchState as SearchUIState.Success).data.toMutableList()
                temp.addAll(it.toSearchItemList())
                searchState = SearchUIState.Success(temp)
            }
        }
    }

    private fun getPersonByName(q: String) {
        searchPage = 1
        searchState = SearchUIState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchPerson.search(q)

            res.onSuccess {
                searchState = SearchUIState.Success(it.toSearchItemList())
            }
        }
    }

    private fun loadMorePersonByName() {
        searchPage++

        viewModelScope.launch(Dispatchers.IO) {
            val res = searchPerson.search(query, searchPage)

            res.onSuccess {
                val temp = (searchState as SearchUIState.Success).data.toMutableList()
                temp.addAll(it.toSearchItemList())
                searchState = SearchUIState.Success(temp)
            }
        }
    }

    fun clearSearchResult() {
        searchState = SearchUIState.Error
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