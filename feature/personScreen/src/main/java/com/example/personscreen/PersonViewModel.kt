package com.example.personscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awards.GetPersonAwardsByDate
import com.example.common.Constants
import com.example.common.multiRequest
import com.example.model.movie.ShortMovie
import com.example.movieScreen.GetMovieByFilter
import com.example.person.GetPersonById
import com.example.ui.uiState.FactUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val getPersonById: GetPersonById,
    private val getMovieByFilter: GetMovieByFilter,
    private val getPersonAwardsByDate: GetPersonAwardsByDate
): ViewModel() {
    var personState by mutableStateOf(PersonUIState.Loading as PersonUIState)
        private set
    var moviesState by mutableStateOf(MovieUIState.Loading as MovieUIState)
        private set
    var countAwards by mutableStateOf<Int?>(null)
        private set
    var factState by mutableStateOf(FactUIState.Loading as FactUIState)
        private set
    var personSpouseState by mutableStateOf(PersonUIState.Loading as PersonUIState)
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
        if (groups.isNotEmpty()) {
            moviesFromGroup = groups[groupsKeys[index]] ?: listOf()
        }
    }

    fun getPerson(id: Int) {
        if (personState is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonById.execute(id)

            res.onSuccess {
                personState = PersonUIState.Success(listOf(it))
                factState = FactUIState.Success(it.facts)
                getGroups(it.movies)
            }
        }
    }

    fun getMovies(personId: Int) {
        if (moviesState is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val query = listOf(
                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                Constants.SORT_TYPE to Constants.SORT_DESC,
                Constants.PERSONS_ID_FIELD to personId.toString()
            )

            val res = getMovieByFilter.execute(query)

            res.onSuccess {
                moviesState = MovieUIState.Success(it)
            }
        }
    }

    fun getCountAwards(personId: Int) {
        if (countAwards == null) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonAwardsByDate.execute(personId)

            res.onSuccess {
                if (it.isNotEmpty()) countAwards = it.size
            }
        }
    }

    fun getSpouses(list: List<Int>) {
        if (personSpouseState is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.Default) {
            val temp = multiRequest(list.map { it.toString() }) {
                getPersonById.execute(it.toInt())
            }

            if (temp.isNotEmpty()) {
                personSpouseState = PersonUIState.Success(temp)
            }
        }
    }

    private fun getGroups(movies: List<ShortMovie>) {
        groups = movies.groupBy { it.enProfession ?: "" }
        groupsKeys = groups.map { it.key }

        if (groups.isNotEmpty()) {
            moviesFromGroup = groups[groupsKeys[0]] ?: listOf()
        }
    }
}