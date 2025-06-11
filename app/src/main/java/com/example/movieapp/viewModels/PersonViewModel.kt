package com.example.movieapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetAward
import com.example.core.domain.usecases.GetMovie
import com.example.core.domain.usecases.GetPerson
import com.example.movieapp.ui.screen.uiState.FactUIState
import com.example.movieapp.ui.screen.uiState.MovieUIState
import com.example.movieapp.ui.screen.uiState.PersonUIState
import com.example.network.module.movie.ShortMovie
import com.example.network.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val getPerson: GetPerson,
    private val getMovie: GetMovie,
    private val getAward: GetAward
): ViewModel() {
    var personState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set
    var moviesState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var countAwards by mutableStateOf<Int?>(null)
        private set
    var factState by mutableStateOf(FactUIState.Loading as FactUIState)
        private set

    var groups by mutableStateOf<Map<String, List<ShortMovie>>>(mapOf())
        private set
    var groupsKeys by mutableStateOf<List<String>>(listOf())
        private set
    var selectedGroup by mutableIntStateOf(0)
        private set
    var moviesFromGroup by mutableStateOf<List<ShortMovie>>(listOf())
        private set

    fun updateSelectedGroup(index: Int) {
        selectedGroup = index
        moviesFromGroup = groups[groupsKeys[index]] ?: listOf()
    }

    fun getPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getPerson.getPersonById(id)
            if (res != null) {
                personState = PersonUIState.Success(listOf(res))
                factState = FactUIState.Success(res.facts)
                getGroups(res.movies)
            }
        }
    }

    fun getMovies(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val query = listOf(
                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                Constants.SORT_TYPE to Constants.SORT_DESC,
                Constants.PERSONS_ID_FIELD to personId.toString()
            )

            val res = getMovie.getByFilter(query)

            if (res.isNotEmpty()) {
                moviesState = MovieUIState.Success(res)
            }
        }
    }

    fun getCountAwards(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getAward.getPersonAwardsByDate(personId)

            if (res.total != 0) {
                countAwards = res.total
            }
        }
    }

    private fun getGroups(movies: List<ShortMovie>) {
        groups = movies.groupBy { it.enProfession ?: "" }
        groupsKeys = groups.map { it.key }
        moviesFromGroup = groups[groupsKeys[0]] ?: listOf()
    }
}