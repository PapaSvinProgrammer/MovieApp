package com.example.personscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awards.GetPersonAwardsByDate
import com.example.utils.Constants
import com.example.utils.multiRequest
import com.example.model.movie.ShortMovie
import com.example.movieScreen.GetMovieByFilter
import com.example.person.GetPersonById
import com.example.ui.uiState.FactUIState
import com.example.ui.uiState.MovieUIState
import com.example.ui.uiState.PersonUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PersonViewModel @Inject constructor(
    private val getPersonById: GetPersonById,
    private val getMovieByFilter: GetMovieByFilter,
    private val getPersonAwardsByDate: GetPersonAwardsByDate
): ViewModel() {
    private val _personState = MutableStateFlow(PersonUIState.Loading as PersonUIState)
    private val _moviesState = MutableStateFlow(MovieUIState.Loading as MovieUIState)
    private val _factState = MutableStateFlow(FactUIState.Loading as FactUIState)
    private val _countAwards = MutableStateFlow<Int?>(null)
    private val _personSpouseState = MutableStateFlow(PersonUIState.Loading as PersonUIState)
    var personState: StateFlow<PersonUIState> = _personState
    val moviesState: StateFlow<MovieUIState> = _moviesState
    val countAwards: StateFlow<Int?> = _countAwards
    val factState: StateFlow<FactUIState> = _factState
    val personSpouseState: StateFlow<PersonUIState> = _personSpouseState

    private val _groups = MutableStateFlow<Map<String, List<ShortMovie>>>(mapOf())
    private val _groupsKeys = MutableStateFlow<List<String>>(listOf())
    private val _selectedGroup = MutableStateFlow(0)
    private val _moviesFromGroup = MutableStateFlow<List<ShortMovie>>(listOf())
    val groups: StateFlow<Map<String, List<ShortMovie>>> = _groups
    val groupsKeys: StateFlow<List<String>> = _groupsKeys
    val selectedGroup: StateFlow<Int> = _selectedGroup
    val moviesFromGroup: StateFlow<List<ShortMovie>> = _moviesFromGroup

    fun updateSelectedGroup(index: Int) {
        _selectedGroup.value = index
        if (groups.value.isNotEmpty()) {
            val currentKey = groupsKeys.value[index]
            _moviesFromGroup.value = groups.value[currentKey] ?: listOf()
        }
    }

    fun getPerson(id: Int) {
        if (personState.value is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonById.execute(id)

            res.onSuccess {
                _personState.value = PersonUIState.Success(listOf(it))
                _factState.value = FactUIState.Success(it.facts)
                getGroups(it.movies)
            }
        }
    }

    fun getMovies(personId: Int) {
        if (moviesState.value is MovieUIState.Success) return

        viewModelScope.launch(Dispatchers.IO) {
            val query = listOf(
                Constants.SORT_FIELD to Constants.RATING_KP_FIELD,
                Constants.SORT_TYPE to Constants.SORT_DESC,
                Constants.PERSONS_ID_FIELD to personId.toString()
            )

            val res = getMovieByFilter.execute(query)

            res.onSuccess {
                _moviesState.value = MovieUIState.Success(it)
            }
        }
    }

    fun getCountAwards(personId: Int) {
        if (countAwards.value == null) return

        viewModelScope.launch(Dispatchers.IO) {
            val res = getPersonAwardsByDate.execute(personId)

            res.onSuccess {
                if (it.isNotEmpty()) {
                    _countAwards.value = it.size
                }
            }
        }
    }

    fun getSpouses(list: List<Int>) {
        if (personSpouseState.value is PersonUIState.Success) return

        viewModelScope.launch(Dispatchers.Default) {
            val temp = multiRequest(list.map { it.toString() }) {
                getPersonById.execute(it.toInt())
            }

            if (temp.isNotEmpty()) {
                _personSpouseState.value = PersonUIState.Success(temp)
            }
        }
    }

    private fun getGroups(movies: List<ShortMovie>) {
        _groups.value = movies.groupBy { it.enProfession ?: "" }
        _groupsKeys.value = groups.value.map { it.key }

        if (groups.value.isNotEmpty()) {
            val currentKey = groupsKeys.value[0]
            _moviesFromGroup.value = groups.value[currentKey] ?: listOf()
        }
    }
}